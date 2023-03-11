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
 *
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
     *
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
     * @param xsdSchema
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
     * @param cl
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
     * @param type
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
     *
     */
    public static void printXml() {
        if (writer != null) {
            System.out.println(writer.toString());
        }
    }

    /**
     * @param obj
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
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T transformToPOJO(Class<T> obj) {
        if (context != null && outputType != null) {
            if (obj == contextClass) {
                try {
                    Unmarshaller unmarshaller = null;
                    Object unmarshalledObject = null;
                    T result = null;
                    switch (outputType) {
                        case StringWriter:
                            unmarshaller = context.createUnmarshaller();
                            if (xsdSchema != null) {
                                unmarshaller.setSchema(objectSchema);
                            }
                            reader = new StringReader(writer.toString());
                            unmarshalledObject = unmarshaller.unmarshal(reader);
                            result = obj.newInstance();
                            return obj.cast(unmarshalledObject);
                        case File:
                            unmarshaller = context.createUnmarshaller();
//                            unmarshaller.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, Boolean.TRUE);
//                            unmarshaller.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, Boolean.TRUE);
                            if (xsdSchema != null) {
                                unmarshaller.setSchema(objectSchema);
                            }
                            unmarshalledObject = unmarshaller.unmarshal(new File(filepath));
                            result = obj.newInstance();
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