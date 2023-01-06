/*
 * Copyright 2015-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine;

/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.subpackage.SuperClassWithPackagePrivateLifecycleMethodInDifferentPackageTestCase;

/**
 * Integration tests that explicitly demonstrate the overriding and superseding
 * rules for lifecycle methods in the {@link JupiterTestEngine}.
 *
 * @since 5.9
 */
class LifecycleMethodOverridingAndSupersedingTests {

	@Nested
	@DisplayName("A package-private lifecycle super-method can be overridden by")
	class PackagePrivateSuperClassTests {

		@Nested
		@DisplayName("a protected lifecycle method in the derived class")
		class ProtectedExtendsPackagePrivateLifecycleMethod
				extends SuperClassWithPackagePrivateLifecycleMethodTestCase {

			@Override
			@BeforeEach
			protected void beforeEach() {
			}

		}

		@Nested
		@DisplayName("a package-private lifecycle method in the derived class")
		class PackagePrivateExtendsPackagePrivateLifecycleMethod
				extends SuperClassWithPackagePrivateLifecycleMethodTestCase {

			@Override
			@BeforeEach
			void beforeEach() {
			}

		}

		@Nested
		@DisplayName("a public lifecycle method in the derived class")
		class PublicExtendsPackagePrivateLifecycleMethod extends SuperClassWithPackagePrivateLifecycleMethodTestCase {

			@Override
			@BeforeEach
			public void beforeEach() {
			}

		}
	}

	@Nested
	@DisplayName("A package-private lifecycle super-method from a different package can be superseded by")
	class PackagePrivateSuperClassInDifferentPackageTests {

		@Nested
		@DisplayName("a protected lifecycle method in the derived class")
		class ProtectedExtendsPackagePrivateLifecycleMethod
				extends SuperClassWithPackagePrivateLifecycleMethodInDifferentPackageTestCase {

			// @Override
			@BeforeEach
			protected void beforeEach() {
			}

		}

		@Nested
		@DisplayName("a package-private lifecycle method in the derived class")
		class PackagePrivateExtendsPackagePrivateLifecycleMethod
				extends SuperClassWithPackagePrivateLifecycleMethodInDifferentPackageTestCase {

			// @Override
			@BeforeEach
			void beforeEach() {
			}

		}

		@Nested
		@DisplayName("a public lifecycle method in the derived class")
		class PublicExtendsPackagePrivateLifecycleMethod
				extends SuperClassWithPackagePrivateLifecycleMethodInDifferentPackageTestCase {

			// @Override
			@BeforeEach
			public void beforeEach() {
			}

		}
	}

	@Nested
	@DisplayName("A protected lifecycle super-method can be overridden by")
	class ProtectedSuperClassTests {

		@Nested
		@DisplayName("a protected lifecycle method in the derived class")
		class ProtectedExtendsPackagePrivate extends SuperClassWithProtectedLifecycleMethodTestCase {

			@Override
			@BeforeEach
			protected void beforeEach() {
			}

		}

		@Nested
		@DisplayName("a public lifecycle method in the derived class")
		class PublicExtendsPackagePrivate extends SuperClassWithProtectedLifecycleMethodTestCase {

			@Override
			@BeforeEach
			public void beforeEach() {
			}

		}
	}

	@Nested
	@DisplayName("A public lifecycle super-method can be overridden by")
	class PublicSuperClassTests {

		@Nested
		@DisplayName("a public lifecycle method in the derived class")
		class PublicExtendsPackagePrivate extends SuperClassWithPublicLifecycleMethodTestCase {

			@Override
			@BeforeEach
			public void beforeEach() {
			}

		}
	}

}

// -------------------------------------------------------------------------

class SuperClassWithPackagePrivateLifecycleMethodTestCase {

	@BeforeEach
	void beforeEach() {
		fail();
	}

	@Test
	void test() {
	}

}

class SuperClassWithProtectedLifecycleMethodTestCase {

	@BeforeEach
	protected void beforeEach() {
		fail();
	}

	@Test
	void test() {
	}

}

class SuperClassWithPublicLifecycleMethodTestCase {

	@BeforeEach
	public void beforeEach() {
		fail();
	}

	@Test
	void test() {
	}

}