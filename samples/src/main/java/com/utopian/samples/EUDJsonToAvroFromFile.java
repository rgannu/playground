package com.utopian.samples;

/**
 * Created by garamasu on 27-Jul-15.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.util.Utf8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class EUDJsonToAvroFromFile {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String json = new String(Files.readAllBytes(Paths.get(EUDJsonToAvroFromFile.class.getResource("/eud-report-1.json").toURI())), Charset.forName("UTF-8"));
        String schemastr = new String(Files.readAllBytes(Paths.get(EUDJsonToAvroFromFile.class.getResource("/schema.avro").toURI())), Charset.forName("UTF-8"));
        Schema schema = new Schema.Parser().setValidate(true).parse(schemastr);

        byte[] avroByteArray = JsonConverter.convertToAvro(json.getBytes(), schema);
        GenericRecord result = JsonConverter.getGenericRecord(avroByteArray, schema);

        String jsonRetrieved = new String(JsonConverter.convertToJson(avroByteArray, schema));
        System.out.println(jsonRetrieved);

        System.out.println(result.get("timestamp_ms"));
        System.out.println(result.get("mac"));
        System.out.println(result.get("linkSpeed_Mbps"));
        System.out.println(result.get("connected_BSSID").toString());

        Map<Utf8, Utf8> customInfo = (HashMap<Utf8, Utf8>) result.get("customInfo");

        System.out.println(customInfo.get(new Utf8("sessionId")).toString());
        System.out.println(customInfo.get(new Utf8("location")).toString());
        System.out.println(customInfo.get(new Utf8("endUserDeviceId")).toString());

        GenericData.Array<GenericRecord> networks = (GenericData.Array<GenericRecord>) result.get("networks");
        for (int i = 0; i < networks.size(); i++) {
            System.out.println(networks.get(i).get("ssid"));
            GenericData.Array<GenericRecord> rssi_dBm = (GenericData.Array<GenericRecord>) networks.get(i).get("rssi_dBm");
            for (int j = 0; j < rssi_dBm.size(); j++) {
                System.out.println(rssi_dBm.get(j));
            }
        }

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

        GenericDatumWriter<Object> w = new GenericDatumWriter<Object>(schema);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Encoder e = EncoderFactory.get().binaryEncoder(outputStream, null);

        w.write(datum, e);
        e.flush();

        return outputStream.toByteArray();
    }

}