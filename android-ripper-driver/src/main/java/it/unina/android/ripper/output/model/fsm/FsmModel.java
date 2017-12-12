/**
* The MIT License
* 
* Copyright (c) 2014-2017 REvERSE, REsEarch gRoup of Software Engineering @ the University of Naples Federico II, http://reverse.dieti.unina.it/
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
* 
**/

package it.unina.android.ripper.output.model.fsm;

import it.unina.android.shared.ripper.model.state.ActivityDescription;
import java.util.Collection;

/**
 *
 * @author Artur
 */
public class FsmModel {
    private Collection<ActivityDescription> states;
    private Collection<FSMTransition> transitions;

    public Collection<ActivityDescription> getStates() {
        return states;
    }

    public void setStates(Collection<ActivityDescription> states) {
        this.states = states;
    }

    public Collection<FSMTransition> getTransitions() {
        return transitions;
    }

    public void setTransitions(Collection<FSMTransition> transitions) {
        this.transitions = transitions;
    }
}
