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

package org.projectmaxs.module.tasker;

import android.app.IntentService;
import android.content.Intent;

import org.projectmaxs.shared.global.Message;
import org.projectmaxs.shared.global.util.Log;
import org.projectmaxs.shared.module.MainUtil;

public class ResponseMessageIntentService extends IntentService {

    private static Log LOG = Log.getLog();

    public ResponseMessageIntentService() {
        super("Response Message Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String action = intent.getAction();
        switch (action) {
            case "org.projectmaxs.module.tasker.SEND_RESPONSE_MESSAGE":
                int id = intent.getIntExtra("ID", Integer.MIN_VALUE);
                if (id == Integer.MIN_VALUE) {
                    LOG.w("ID extra (int) not set");
                    return;
                }
                String body = intent.getStringExtra("BODY");
                if (body == null || body.isEmpty()) {
                    LOG.w("BODY extra not set or empty");
                    return;
                }
                Message message = new Message(body, id);
                MainUtil.send(message, this.getApplicationContext());
                break;
            default:
                LOG.w("Unkown action: " + action);
        }

    }
}
