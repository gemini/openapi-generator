package org.openapitools.codegen.languages;

import org.openapitools.codegen.*;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.MapProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.parameters.Parameter;

import java.io.File;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScalaPlayClientCodegen extends AbstractScalaCodegen implements CodegenConfig {
    protected String mainPackage = "org.openapitools.client";
    protected String groupId = "org.openapitools";
    protected String artifactId = "openapi-client";
    protected String artifactVersion = "1.0.0";
    protected String resourcesFolder = "src/main/resources";

    public static final String PROJECT_NAME = "projectName";

    static final Logger LOGGER = LoggerFactory.getLogger(ScalaPlayClientCodegen.class);

    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    public String getName() {
        return "scala-play";
    }

    public String getHelp() {
        return "Generates a scala-play client.";
    }

    public ScalaPlayClientCodegen() {
        super();

        outputFolder = "generated-code" + File.separator + "scala-play";
        modelTemplateFiles.put("model.mustache", ".scala");
        apiTemplateFiles.put("api.mustache", ".scala");
        embeddedTemplateDir = templateDir = "scala-play-client";
        apiPackage = mainPackage + ".api";
        modelPackage = mainPackage + ".model";

        supportingFiles.add(new SupportingFile("build.sbt.mustache", "", "build.sbt"));
        // Config
        //        supportingFiles.add(new SupportingFile("reference.conf.mustache", resourcesFolder, "reference.conf"));
        // DevConfig
        //        supportingFiles.add(new SupportingFile("development.conf.mustache", resourcesFolder, "development.conf"));
        // Bazel Files
        //        supportingFiles.add(new SupportingFile("BUILD.bazel.mustache", "", "BUILD.bazel"));

        setReservedWordsLowerCase(
                Arrays.asList(
                        "abstract", "case", "catch", "class", "def", "do", "else", "extends",
                        "false", "final", "finally", "for", "forSome", "if", "implicit",
                        "import", "lazy", "match", "new", "null", "object", "override", "package",
                        "private", "protected", "return", "sealed", "super", "this", "throw",
                        "trait", "try", "true", "type", "val", "var", "while", "with", "yield")
        );

        additionalProperties.put(CodegenConstants.GROUP_ID, groupId);
        additionalProperties.put(CodegenConstants.ARTIFACT_ID, artifactId);
        additionalProperties.put(CodegenConstants.ARTIFACT_VERSION, artifactVersion);

        importMapping.remove("Seq");
        importMapping.remove("List");
        importMapping.remove("Set");
        importMapping.remove("Map");

        typeMapping = new HashMap<>();
        typeMapping.put("array", "Seq");
        typeMapping.put("set", "Set");
        typeMapping.put("boolean", "Boolean");
        typeMapping.put("string", "String");
        typeMapping.put("int", "Int");
        typeMapping.put("integer", "Int");
        typeMapping.put("long", "Long");
        typeMapping.put("float", "Float");
        typeMapping.put("byte", "Byte");
        typeMapping.put("short", "Short");
        typeMapping.put("char", "Char");
        typeMapping.put("double", "Double");
        typeMapping.put("object", "Any");
        typeMapping.put("file", "File");
        typeMapping.put("binary", "File");
        typeMapping.put("number", "Long");
        typeMapping.put("decimal", "BigDecimal");

        instantiationTypes.put("array", "ListBuffer");
        instantiationTypes.put("map", "Map");

        cliOptions.add(new CliOption("mainPackage", "Top-level package name, which defines 'apiPackage', 'modelPackage', 'invokerPackage'").defaultValue("org.openapitools.client"));
    }

    @Override
    public void processOpts() {
        super.processOpts();

        if (additionalProperties.containsKey("mainPackage")) {
            mainPackage = additionalProperties.get("mainPackage").toString();
            apiPackage = mainPackage + ".api";
            modelPackage = mainPackage + ".model";
        }
        if (additionalProperties.containsKey(CodegenConstants.GROUP_ID)) {
            groupId = additionalProperties.get(CodegenConstants.GROUP_ID).toString();
        }
        if (additionalProperties.containsKey(CodegenConstants.ARTIFACT_ID)) {
            artifactId = additionalProperties.get(CodegenConstants.GROUP_ID).toString();
        }
        if (additionalProperties.containsKey(CodegenConstants.ARTIFACT_VERSION)) {
            artifactVersion = additionalProperties.get(CodegenConstants.GROUP_ID).toString();
        }
    }
}
