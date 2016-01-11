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

import org.projectmaxs.shared.global.Message;
import org.projectmaxs.shared.mainmodule.Command;
import org.projectmaxs.shared.module.MAXSModuleIntentService;
import org.projectmaxs.shared.module.SubCommand;
import org.projectmaxs.shared.module.SupraCommand;

public abstract class TaskerExecuteAbstract extends SubCommand {

    public TaskerExecuteAbstract(SupraCommand supraCommand, String name,
                                    boolean isDefaultWithoutArguments, boolean isDefaultWithArguments) {
        super(supraCommand, name, isDefaultWithoutArguments, isDefaultWithArguments);
    }

    @Override
    public Message execute(String arguments, Command command, MAXSModuleIntentService service)
            throws Throwable {

        Context c = service.getApplicationContext();

        TaskerIntent.Status taskerStatus = TaskerIntent.testStatus(c);
        if (taskerStatus != TaskerIntent.Status.OK)
            return new Message("Failed. Tasker status: " + taskerStatus.toString());

        String[] argsSplit = command.getArgs().split("  ");
        if (argsSplit.length == 0)
            return new Message("Failed. Missing task name.");

        String taskName = argsSplit[0];

        TaskerIntent intent = new TaskerIntent( taskName );

        intent.addLocalVariable("%maxsid", Integer.toString(command.getId()));

        for (int i = 1; i < argsSplit.length; i++) {
            String param = argsSplit[i];
            if (param.startsWith("%")) {
                String[] paramParts = param.split("=", 2);
                intent.addLocalVariable(paramParts[0], paramParts[1]);
            } else
                intent.addParameter(param);
        }

        c.sendBroadcast(intent);

        return new Message("Task execution requested: " + taskName);
    }
}
