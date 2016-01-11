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

import org.projectmaxs.module.tasker.ModuleService;
import org.projectmaxs.shared.global.Message;
import org.projectmaxs.shared.mainmodule.Command;
import org.projectmaxs.shared.module.MAXSModuleIntentService;

public class TaskerExecuteSilent extends TaskerExecuteAbstract {
    public TaskerExecuteSilent() {
        super(ModuleService.sTASKER, "executesilent", false, false);
        setHelp("<task name>␣␣<par1>␣␣<par2>...",
                "Execute a task silently. The task name and parameters needs to be seperated with two spaces. Local variables could be set using %var=val.");
    }
    @Override
    public Message execute(String arguments, Command command, MAXSModuleIntentService service) throws Throwable {
        Message result = super.execute(arguments, command, service);
        return null;
    }
}
