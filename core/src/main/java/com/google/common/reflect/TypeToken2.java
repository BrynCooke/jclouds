/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.common.reflect;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

/*
 * FIXME: remove this class ASAP!
 *
 * Evil stuff, adapted from https://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/reflect/TypeToken.java#236.
 * See https://issues.apache.org/jira/browse/JCLOUDS-427 and
 * https://code.google.com/p/guava-libraries/issues/detail?id=1635
 */
public class TypeToken2<T> extends TypeToken<T> {
   @SuppressWarnings("unchecked")
   public <X, Y> TypeToken<T> where(TypeParameter<X> typeParam1, 
         TypeToken<X> typeArg1, TypeParameter<Y> typeParam2, TypeToken<Y> typeArg2) {
      // resolving both parameters in one shot seems to work around 1635
      TypeResolver resolver = new TypeResolver()
            .where(ImmutableMap.of(typeParam1.typeVariable, typeArg1.getType(), typeParam2.typeVariable, typeArg2.getType()));
      return (TypeToken<T>) TypeToken.of(resolver.resolveType(getType()));
   }  

   public <X, Y> TypeToken<T> where(TypeParameter<X> typeParam1, Class<X> typeArg1,
         TypeParameter<Y> typeParam2, Class<Y> typeArg2) {
      return where(typeParam1, of(typeArg1), typeParam2, of(typeArg2));
   } 
}