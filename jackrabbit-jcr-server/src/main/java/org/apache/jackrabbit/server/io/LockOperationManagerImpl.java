/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jackrabbit.server.io;

import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.lock.LockManager;

import java.util.ArrayList;
import java.util.List;

public class LockOperationManagerImpl implements LockOperationManager {

    private static LockOperationManager DEFAULT_MANAGER;

    private final List<LockHandler> lockHandlers = new ArrayList<LockHandler>();


    /**
     * @see LockOperationManager#addLockHandler(org.apache.jackrabbit.server.io.LockHandler)
     */
    public void addLockHandler(LockHandler lockHandler) {
        if (lockHandler == null) {
            throw new IllegalArgumentException("'null' is not a valid LockHandler.");
        }
        lockHandlers.add(lockHandler);

    }

    /**
     * @see LockOperationManager#getLockHandlers()
     */
    public LockHandler[] getLockHandlers() {
        return lockHandlers.toArray(new LockHandler[lockHandlers.size()]);
    }

    @Override
    public LockManager getLockManager(LockContext lockContext, DavResource member) {
        LockHandler[] lockHandlers = getLockHandlers();
        for (int i = 0; i < lockHandlers.length; i++) {
            LockHandler lh = lockHandlers[i];
            if (lh.canLock(lockContext, member)) {
                return lh.getLockManager();
            }
        }
        return null;
    }

    /**
     * Returns this lock manager singleton
     */
    public static LockOperationManager getDefaultManager() {
        if (DEFAULT_MANAGER == null) {
            LockOperationManager manager = new LockOperationManagerImpl();
            manager.addLockHandler(new DefaultHandler());
            DEFAULT_MANAGER = manager;
        }
        return DEFAULT_MANAGER;
    }

}
