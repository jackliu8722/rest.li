/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/**
 * $Id: SystemClock.java 33147 2007-11-18 22:29:12Z dmccutch $ */
package com.linkedin.util.clock;

import java.io.Serializable;

/**
 * Implementation of a clock using the system clock.
 */
public class SystemClock implements Clock
{
  private static final SystemClock INSTANCE = new SystemClock();

  public static SystemClock instance()
  {
    return INSTANCE;
  }

  /**
   * Constructor
   */
  private SystemClock()
  {
  }

  /**
   * @return the current time of this clock in milliseconds.
   */
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
}
