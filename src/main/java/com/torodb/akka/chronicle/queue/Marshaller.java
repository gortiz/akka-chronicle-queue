/*
 * Copyright 2017 8Kdata Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.torodb.akka.chronicle.queue;

import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.wire.DocumentContext;

/**
 *
 */
public interface Marshaller<T> extends WriteMarshaller<T>, ReadMarshaller<T> {

  public default void write(ExcerptAppender appender, T element) {
    try (DocumentContext dc = appender.writingDocument()) {
      this.write(dc.wire(), element);
    }
  }

  public default T read(ExcerptTailer tailer) {
    try (DocumentContext dc = tailer.readingDocument()) {
      return this.read(dc.wire());
    }
  }

}