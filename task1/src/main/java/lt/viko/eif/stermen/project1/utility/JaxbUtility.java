package lt.viko.eif.stermen.project1.utility;

import com.fasterxml.classmate.AnnotationConfiguration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;

import jdk.jshell.spi.ExecutionControl;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * A utility that allows the marshalling and unmarshalling of mapped classes to and from XML format.
 */
public final class JaxbUtility {
    private static JAXBContext context = null;
    private static Marshaller marshaller = null;
    private static Unmarshaller unmarshaller = null;
    private static Class contextClass = null;
    private static JaxbUtilityOutputType outputType = null;
    private static StringWriter writer = null;
    private static StringReader reader = null;
    private static String xsdSchema = null;
    private static SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    private static Schema objectSchema = null;
    private static String filepath = null;

    /**
     * Utility class cannot have an instance. Throw exception if instantiated.
     */
    private JaxbUtility() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and it cannot be instantiated.");
    }

    public static StringWriter getWriter() {
        return writer;
    }

    public static void setWriter(StringWriter writer) {
        JaxbUtility.writer = writer;
    }

    public static StringReader getReader() {
        return reader;
    }

    public static void setReader(StringReader reader) {
        JaxbUtility.reader = reader;
    }

    public static String getXsdSchema() {
        return xsdSchema;
    }

    /**
     * If an .xsd schema exists for the object tree it should be provided with this method.
     * Further marshalling and unmarshalling will be validated with the provided schema.
     * @param xsdSchema file location of .xsd schema relative to the programs execution location.
     */
    public static void setXsdSchema(String xsdSchema) {
        JaxbUtility.xsdSchema = xsdSchema;
        try {
            objectSchema = schemaFactory.newSchema(new File(xsdSchema));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFilepath() {
        return filepath;
    }

    public static void setFilepath(String filepath) {
        JaxbUtility.filepath = filepath;
    }

    /**
     * Sets the context class for all marshalling and unmarshalling methods.
     * @param cl class type
     */
    public static void setContext(Class cl) {
        try {
            contextClass = cl;
            context = JAXBContext.newInstance(cl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the output type by specifying the JaxbUtilityOutputType.
     * @param type enum type of Console, StringWriter, File.
     */
    public static void setOutputType(JaxbUtilityOutputType type) {
        switch (type) {
            case StringWriter:
                writer = new StringWriter();
            default:
                break;
        }
        outputType = type;
    }

    /**
     * Prints the currently stored object in .xml format if it exists.
     */
    public static void printXml() {
        if (writer != null) {
            System.out.println(writer.toString());
        }
    }

    /**
     * Marshal object to .xml format. Validates the object if a .xsd schema is provided.
     * Outputs based on the outputType selected.
     * Console - prints output to console.
     * StringWriter - stores output to writer.
     * File - stores output to file. filepath property must be specified.
     * @param obj the object to be marshalled.
     */
    public static void transformToXML(Object obj) {
        if (context != null && outputType != null) {
            if (obj.getClass() == contextClass) {
                try {
                    marshaller = context.createMarshaller();
                    marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
                    switch (outputType) {
                        case Console:
                            marshaller.marshal(obj, System.out);
                            break;
                        case StringWriter:
                            marshaller.marshal(obj, writer);
                            break;
                        case File:
                            if (filepath != null) {
                                marshaller.marshal(obj, new File(filepath));
                            }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Unmarshal class from .xml format to an object. Validates the object if a .xsd schema is provided.
     * Outputs based on the outputType selected.
     * Console - no implemented.
     * StringWriter - reads from writer.
     * File - reads from file. filepath property must be specified.
     * @param obj provide class type of object that was marshalled.
     * @param <T> type of object to be returned - based on class type.
     * @return transform XML to Object and that object.
     */
    public static <T> T transformToPOJO(Class<T> obj) {
        if (context != null && outputType != null) {
            if (obj == contextClass) {
                try {
                    Unmarshaller unmarshaller = null;
                    Object unmarshalledObject = null;
//                    T result = null;
                    unmarshaller = context.createUnmarshaller();
                    if (xsdSchema != null) {
                        unmarshaller.setSchema(objectSchema);
                    }
                    switch (outputType) {
                        case StringWriter:

                            reader = new StringReader(writer.toString());
                            unmarshalledObject = unmarshaller.unmarshal(reader);
//                            result = obj.newInstance();
                            return obj.cast(unmarshalledObject);
                        case File:
                            unmarshaller = context.createUnmarshaller();
//                            unmarshaller.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, Boolean.TRUE);
//                            unmarshaller.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, Boolean.TRUE);
                            if (xsdSchema != null) {
                                unmarshaller.setSchema(objectSchema);
                            }
                            unmarshalledObject = unmarshaller.unmarshal(new File(filepath));
//                            result = obj.newInstance();
                            return obj.cast(unmarshalledObject);
                        default:
                            throw new ExecutionControl.NotImplementedException("NO FUNCTIONALITY");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}