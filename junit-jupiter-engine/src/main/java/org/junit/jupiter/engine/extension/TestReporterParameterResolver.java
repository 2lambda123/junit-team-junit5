/*
 * Copyright 2015-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.engine.extension;

import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.function.ThrowingConsumer;

/**
 * {@link ParameterResolver} that injects a {@link TestReporter}.
 *
 * @since 5.0
 */
class TestReporterParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return (parameterContext.getParameter().getType() == TestReporter.class);
	}

	@Override
	public TestReporter resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return new TestReporter() {
			@Override
			public void publishEntry(Map<String, String> map) {
				extensionContext.publishReportEntry(map);
			}

			@Override
			public void publishFile(String fileName, ThrowingConsumer<Path> action) {
				extensionContext.publishFile(fileName, action);
			}
		};
	}

}
