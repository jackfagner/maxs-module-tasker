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

import android.database.Cursor;
import android.net.Uri;

import org.projectmaxs.module.tasker.ModuleService;
import org.projectmaxs.shared.global.Message;
import org.projectmaxs.shared.global.messagecontent.CommandHelp;
import org.projectmaxs.shared.mainmodule.Command;
import org.projectmaxs.shared.module.MAXSModuleIntentService;
import org.projectmaxs.shared.module.SubCommand;

public class TaskerGetTasks extends SubCommand {

    public TaskerGetTasks() {
        super(ModuleService.sTASKER, "gettasks", true, false);
        setHelp(CommandHelp.ArgType.NONE, "Get a list of all available Tasker tasks");
    }

    @Override
    public Message execute(String arguments, Command command, MAXSModuleIntentService service)
            throws Throwable {
        String taskList = "";
        Cursor c = service.getContentResolver().query(Uri.parse("content://net.dinglisch.android.tasker/tasks"), null, null, null, null );
        if ( c != null ) {
            int nameCol = c.getColumnIndex( "name" );
            int projNameCol = c.getColumnIndex( "project_name" );
            while ( c.moveToNext() )
                taskList += "\n" + c.getString( projNameCol ) + "/" + c.getString( nameCol );
            c.close();
        }
        return new Message("Tasks: " + taskList);
    }
}
