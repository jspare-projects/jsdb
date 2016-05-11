/*
 * Copyright 2015 TechFull IT Services.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.jspare.jsdbc.samples;

import java.util.ArrayList;
import java.util.List;

import org.jspare.core.container.Inject;
import org.jspare.core.container.MySupport;
import org.jspare.jsdbc.Jsdbc;
import org.jspare.jsdbc.exception.JsdbcException;
import org.jspare.jsdbc.model.Credential;
import org.jspare.jsdbc.model.DataSource;
import org.jspare.jsdbc.model.Domain;
import org.jspare.jsdbc.samples.domain.Person;
import org.junit.Before;
import org.junit.Test;

public class Persist extends MySupport {

	@Inject
	private Jsdbc jsdbc;

	@Before
	public void setup() {

		DataSource dataSource = DataSource.of("system.prd", "http://127.0.0.1", 5732);
		Credential credential = Credential.of("42493677df32f1c7a8f91675d3c4c97f");

		jsdbc.dataSource(dataSource).credential(credential);
	}

	@Test
	public void persist() throws JsdbcException {

		Person person = new Person();
		person.personId("08643467944");
		person.name("Person name");

		jsdbc.persist(person);
	}

	@Test
	public void persistBatch() throws JsdbcException {

		List<Person> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {

			Person person = new Person();
			person.personId(String.valueOf(i));
			person.name("Person name " + i);
			list.add(person);
		}

		jsdbc.persist(Domain.of(Person.class), list);
	}

}
