/**
 * GNU Affero General Public License, version 3
 *
 * Copyright (c) 2014-2017 REvERSE, REsEarch gRoup of Software Engineering @ the University of Naples Federico II, http://reverse.dieti.unina.it/
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package it.unina.android.ripper.states;

import it.unina.android.ripper.comparator.IComparator;
import it.unina.android.shared.ripper.model.state.ActivityDescription;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * List of Activity States traversed during the ripping process.
 *
 * @author Nicola Amatucci - REvERSE
 *
 */
public class ActivityStateList implements Serializable {

    private Map<String, ActivityDescription> activitiesMap = new HashMap<>();
    /**
     * Comparator Instance
     */
    IComparator comparator = null;

    /**
     * Last id assigned to a traversed Activity
     */
    int lastActivityId = 0;

    /**
     * Constructor
     *
     * @param comparator Comparator instance
     */
    public ActivityStateList(IComparator comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * Check if the Activity has been traversed during the ripping process
     *
     * @param activity1 Activity Description
     * @return
     */
    public String containsActivity(ActivityDescription activity1) {
        if (activitiesMap.containsKey(activity1.getId())) {
            for (ActivityDescription activity2 : activitiesMap.values()) {
                if (activity2 == null) {
                    continue;
                }
                if ((Boolean) this.comparator.compare(activity1, activity2)) {
                    return activity2.getId();
                }
            }
        }
        return null;
    }

    /**
     * Get the Id of an equivalent ActivityDescription, if it exists
     *
     * @param activity1 Activity Description
     * @return
     */
    public String getEquivalentActivityStateId(ActivityDescription activity1) {
        return this.containsActivity(activity1);
    }

    /**
     * Add an ActivityDescription and sets its id
     *
     * @param a
     * @return
     */
    public void addActivity(ActivityDescription a) {
        a.setId("a" + (++lastActivityId));
        activitiesMap.put(a.getId(), a);
    }


    public void setComparator(IComparator comparator) {
        this.comparator = comparator;
    }
}
