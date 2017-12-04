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
 **/

package it.unina.android.shared.ripper.output;

import it.unina.android.shared.ripper.model.state.ActivityDescription;
import it.unina.android.shared.ripper.model.state.WidgetDescription;
import it.unina.android.shared.ripper.model.task.TaskList;
import it.unina.android.shared.ripper.model.transition.IEvent;

/**
 * Specifies how the element of the model are serialized 
 * 
 * @author Nicola Amatucci - REvERSE
 *
 */
public interface RipperOutput {
	/**
	 * Output ActivityDescription as a String
	 * @param a
	 * @return
	 */
	public String outputActivityDescription(ActivityDescription a);
	
	/**
	 * Output ActivityDescription and TaskList as a String
	 * 
	 * @param a
	 * @param t
	 * @return
	 */
	public String outputActivityDescriptionAndPlannedTasks(ActivityDescription a, TaskList t);
	
	/**
	 * Output WidgetDescription as a String
	 * 
	 * @param a
	 * @return
	 */
	public String outputWidgetDescription(WidgetDescription a);
	
	
	/**
	 * Output Fired Event as a String
	 * 
	 * @param evt
	 * @return
	 */
	public String outputFiredEvent(IEvent evt);	
}
