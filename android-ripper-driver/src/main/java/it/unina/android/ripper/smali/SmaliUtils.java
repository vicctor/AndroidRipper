package it.unina.android.ripper.smali;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author Artur
 */
public class SmaliUtils {
    private final static Pattern CLASS_DEF_PATTERN = Pattern.compile("\\.class\\s+(public\\s+)?(final\\s+)?L([^;]+);");
    private final static Pattern METHOD_DETECTOR_PATTERN = Pattern.compile("\\.method\\s((public|private|protected)\\s)(final\\s)?(.*)\\(.*");
    private final static Pattern END_METHOD_PATTENR = Pattern.compile("\\.end method$");
    private final static Pattern PROLOGUE_PATTERN = Pattern.compile("\\s+\\.prologue$");
    private final static Pattern REGISTERS_PATTERN = Pattern.compile("\\s+\\.registers\\s+(\\d+)");
    
    

    static class ProcessState {

        public String currentMethod;
        public String currentClassName;
        public int lineNo;
        public int methodFirstLine;
        public boolean processingMethod;
        public ArrayList<String> output = new ArrayList<>();
    }

    public static void injectTrace(File smaliFile) {
        final ProcessState state = new ProcessState();
        state.processingMethod = false;

        try (Stream<String> stream = Files.lines(smaliFile.toPath())) {
            stream.forEachOrdered(line -> process(line, state));
        } catch (IOException ex) {
            Logger.getLogger(SmaliUtils.class.getName()).log(Level.SEVERE, "Could not instrument smali file", ex);
        }

        try {
            Files.write(smaliFile.toPath(), (Iterable<String>) state.output);
        } catch (IOException ex) {
            Logger.getLogger(SmaliUtils.class.getName()).log(Level.SEVERE, "Could not update file", ex);
        }
    }

    private static void process(String line, ProcessState state) {
        Matcher methodMatcher = METHOD_DETECTOR_PATTERN.matcher(line);
        Matcher endMethod = END_METHOD_PATTENR.matcher(line);
        Matcher prologueMatcher = PROLOGUE_PATTERN.matcher(line);
        Matcher registersMatcher = REGISTERS_PATTERN.matcher(line);
        Matcher classNameMatcher = CLASS_DEF_PATTERN.matcher(line);
        if (registersMatcher.matches()) {
            line = updateRegistersStatement(registersMatcher);
            state.output.add(line);
        } else 
        if (methodMatcher.matches()) {
            state.output.add(line);
            state.processingMethod = true;
            state.methodFirstLine = state.lineNo;
            state.currentMethod = methodMatcher.group(4);
        } else
        if (prologueMatcher.matches() && state.processingMethod) {
            state.output.add(line);
            state.output.add(logInjection("ENTER", state.currentClassName, state.currentMethod));
        } else 
        if (endMethod.matches()) {
            state.output.add(line);
            state.processingMethod = false;
        } else 
        if (classNameMatcher.matches()) {
            state.output.add(line);
            state.currentClassName = classNameMatcher.group(3);
        } else {
            state.output.add(line);
        }
    }

    private static String updateRegistersStatement(Matcher registerMatcher) {
        String valStr = registerMatcher.group(1);
        int nRegs = Integer.parseInt(valStr);
        if (nRegs < 4) {
            nRegs += 2;
        }
        return "    .registers " + nRegs;
    }

    private static String logInjection(String action, String className, String methodName) {
        return "\r\n"
                + "    const-string/jumbo v0, \"TRACE\"\r\n\r\n"
                + "    const-string/jumbo v1, \"" + className + ":" + methodName + ":" + action + " \"\r\n\r\n"
                + "    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I\r\n\r\n";
    }

    public static void injectTraceOnProject(File dir, FileFilter filter) {
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                injectTraceOnProject(f, filter);
            }
        } else if (dir.isFile() && dir.getName().endsWith("smali") && filter.accept(dir)) {
            injectTrace(dir);
        }
    }
    
    public static void injectTraceOnProject(File dir) {
                injectTraceOnProject(dir, fi -> true);
    }
}
