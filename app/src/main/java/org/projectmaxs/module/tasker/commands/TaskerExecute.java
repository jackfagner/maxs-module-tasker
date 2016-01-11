/*
    This file is part of Project MAXS.

    MAXS and its modules is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MAXS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with MAXS.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.projectmaxs.module.tasker.commands;

import android.content.Context;

import net.dinglisch.android.tasker.TaskerIntent;

import org.projectmaxs.module.tasker.ModuleService;
import org.projectmaxs.shared.global.Message;
import org.projectmaxs.shared.global.messagecontent.CommandHelp;
import org.projectmaxs.shared.mainmodule.Command;
import org.projectmaxs.shared.module.MAXSModuleIntentService;
import org.projectmaxs.shared.module.SubCommand;

public class TaskerExecute extends TaskerExecuteAbstract {
    public TaskerExecute() {
        super(ModuleService.sTASKER, "execute", false, true);
        setHelp("<task name>␣␣<par1>␣␣<par2>...",
                "Execute a task. The task name and parameters needs to be seperated with two spaces. Local variables could be set using %var=val.");
    }
}
