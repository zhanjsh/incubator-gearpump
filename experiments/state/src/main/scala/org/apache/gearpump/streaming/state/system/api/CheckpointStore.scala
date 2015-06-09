/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.gearpump.streaming.state.system.api

import org.apache.gearpump.TimeStamp
import org.apache.gearpump.cluster.UserConfig
import org.apache.gearpump.streaming.task.TaskContext

/**
 * CheckpointStore persistently stores mapping of timestamp to checkpoint
 * it's possible that two checkpoints have the same timestamp
 * CheckpointStore needs to handle this either during write or read
 */
trait CheckpointStore {

  def write(timeStamp: TimeStamp, checkpoint: Array[Byte]): Unit

  def read(timestamp: TimeStamp): Option[Array[Byte]]

  def close(): Unit
}

trait CheckpointStoreFactory {
  def getCheckpointStore(conf: UserConfig,
                         taskContext: TaskContext): CheckpointStore
}

