package org.openapitools.codegen.scala.play;

import org.openapitools.codegen.AbstractOptionsTest;
import org.openapitools.codegen.CodegenConfig;
import org.openapitools.codegen.languages.ScalaPlayClientCodegen;
import org.openapitools.codegen.options.ScalaPlayClientCodegenOptionsProvider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScalaPlayClientCodegenOptionsTest extends AbstractOptionsTest {
    private ScalaPlayClientCodegen codegen = mock(ScalaPlayClientCodegen.class, mockSettings);

    public ScalaPlayClientCodegenOptionsTest() {
        super(new ScalaPlayClientCodegenOptionsProvider());
    }

    @Override
    protected CodegenConfig getCodegenConfig() {
        return codegen;
    }

    @SuppressWarnings("unused")
    @Override
    protected void verifyOptions() {
        // TODO: Complete options using Mockito
        // verify(codegen).someMethod(arguments)
    }
}

