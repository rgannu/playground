package com.utopian.samples;

/**
 * Created by garamasu on 27-Jul-15.
 */

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class TestOptionalAvro {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String json ="{\n" +
				"  \"reception_timestamp\": 1404140400000,\n" +
				"  \"mac\": \"aaaa\",\n" +
				"  \"homeNetworkId\": \"home-1\"\n" +
				"}";
		String schemastr = "{\n" +
                "    \"namespace\": \"com.alu.sample\",\n" +
                "    \"name\": \"optional_data\",\n" +
                "    \"type\": \"record\",\n" +
                "    \"fields\": [\n" +
                "        {\n" +
                "            \"name\": \"reception_timestamp\",\n" +
                "            \"type\": \"long\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"mac\",\n" +
                "            \"type\": \"string\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"homeNetworkId\",\n" +
                "            \"type\": \"string\"\n" +
                "        }\n" +
                "    ]\n" +
                "}\n";

		byte[] avroByteArray = fromJsonToAvro(json, schemastr);

        GenericRecord result = getGenericRecord(schemastr, avroByteArray);

		System.out.println(result.get("reception_timestamp"));
		System.out.println(result.get("mac").toString());
		System.out.println(result.get("homeNetworkId").toString());
	}

    private static GenericRecord getGenericRecord(String schemastr, byte[] avroByteArray) throws java.io.IOException {
        Schema schema = new Schema.Parser().setValidate(true).parse(schemastr);
        DatumReader<GenericRecord> reader1 = new GenericDatumReader<GenericRecord>(schema);

        Decoder decoder1 = DecoderFactory.get().binaryDecoder(avroByteArray, null);
        return reader1.read(null, decoder1);
    }

    /**
	 * @param json
	 * @param schemastr
	 * @throws Exception
	 */
	static byte[] fromJsonToAvro(String json, String schemastr) throws Exception {

		InputStream input = new ByteArrayInputStream(json.getBytes());
		DataInputStream din = new DataInputStream(input);

		Schema schema = new Schema.Parser().parse(schemastr);
		Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);

		DatumReader<Object> reader = new GenericDatumReader<Object>(schema);
		Object datum = reader.read(null, decoder);

		GenericDatumWriter<Object>  w = new GenericDatumWriter<Object>(schema);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Encoder e = EncoderFactory.get().binaryEncoder(outputStream, null);

		w.write(datum, e);
		e.flush();

		return outputStream.toByteArray();
	}
}