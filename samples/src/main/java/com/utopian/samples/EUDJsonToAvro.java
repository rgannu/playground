package com.utopian.samples;

/**
 * Created by garamasu on 27-Jul-15.
 */

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class EUDJsonToAvro {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String json ="{\n" + "    \"timestamp_ms\": 1404140400000,\n" + "    \"mac\": \"FC:15:B4:01:02:03\",\n" + "    \"linkSpeed_Mbps\": 1.3,\n"
				+ "    \"connected_BSSID\": \"EF:50:E8:01:02:14\",\n" + "    \"customInfo\": {\n" + "            \"location\": \"living room\",\n"
				+ "            \"cafId\" : \"\",\n" + "            \"sessionId\" : \"\"\n" + "        },\n" + "    \"networks\": [\n" + "        {\n"
				+ "            \"ssid\": \"Neighbour SSID\",\n" + "            \"bssid\": \"D8:50:E6:01:02:03\",\n" + "            \"rssi_dBm\": [\n"
				+ "                -45.4,\n" + "                -48.24\n" + "            ],\n" + "            \"channel\": 1,\n"
				+ "            \"freq_MHz\": 2412\n" + "        },\n" + "        {\n" + "            \"ssid\": \"My_SSID\",\n"
				+ "            \"bssid\": \"EF:50:E8:01:02:14\",\n" + "            \"rssi_dBm\": [\n" + "                -47.3,\n"
				+ "                -49.23,\n" + "                -50.12,\n" + "                -48.12\n" + "            ],\n"
				+ "            \"channel\": 13,\n" + "            \"freq_MHz\": 2472\n" + "        },\n" + "        {\n"
				+ "            \"ssid\": \"My_SSID_5.0GHz\",\n" + "            \"bssid\": \"DD:33:12:EF:11:01\",\n" + "            \"rssi_dBm\": [\n"
				+ "                -37.3,\n" + "                -49.02,\n" + "                -47.1\n" + "            ],\n"
				+ "            \"channel\": 36,\n" + "            \"freq_MHz\": 5180\n" + "        },\t\t\n" + "        {\n"
				+ "            \"ssid\": \"Another Neighbour SSID\",\n" + "            \"bssid\": \"C9:53:F2:00:A2:FE\",\n"
				+ "            \"rssi_dBm\": [\n" + "                -55.23,\n" + "                -49.99,\n" + "                -52.24\n"
				+ "            ],\n" + "            \"channel\": 36,\n" + "            \"freq_MHz\": 5180\n" + "        },\n" + "        {\n"
				+ "            \"ssid\": \"My_SSID-2 same BSSID as My_SSID\",\n" + "            \"bssid\": \"EF:50:E8:01:02:14\",\n"
				+ "            \"rssi_dBm\": [\n" + "                -42.3,\n" + "                -49.29,\n" + "                -50.32,\n"
				+ "                -47.02\n" + "            ],\n" + "            \"channel\": 13,\n" + "            \"freq_MHz\": 2472\n"
				+ "        }\t\t\n" + "    ]\n" + "}";
		String schemastr = "{\n" + "    \"namespace\": \"com.alu.hal.sbi.json\",\n" + "    \"name\": \"eud_data\",\n" + "    \"type\": \"record\",\n"
				+ "    \"fields\": [\n" + "        {\"name\": \"timestamp_ms\", \"type\" : \"long\"},\n"
				+ "        {\"name\": \"mac\", \"type\" : \"string\"},\n" + "        {\"name\": \"linkSpeed_Mbps\", \"type\" : \"double\"},\n"
				+ "        {\"name\": \"connected_BSSID\", \"type\" : \"string\"},\n" + "        {\"name\": \"customInfo\", \"type\" : {\n"
				+ "                \"type\" : \"record\",\n" + "                \"name\" : \"customInfo_record\",\n"
				+ "                \"fields\" : [\n" + "                    {\"name\": \"location\", \"type\": \"string\"},\n"
				+ "                    {\"name\": \"cafId\", \"type\": \"string\"},\n"
				+ "                    {\"name\": \"sessionId\", \"type\": \"string\"}\n" + "                ]\n" + "        }},\n"
				+ "        {\"name\": \"networks\", \"type\" : {\n" + "                \"type\" : \"array\",\n" + "                \"items\" : {\n"
				+ "                    \"type\" : \"record\",\n" + "                    \"name\" : \"networks_record\",\n"
				+ "                    \"fields\" : [\n" + "                        {\"name\": \"ssid\", \"type\": \"string\"},\n"
				+ "                        {\"name\": \"bssid\", \"type\": \"string\"},\n"
				+ "                        {\"name\": \"rssi_dBm\", \"type\": {\n" + "                            \"type\" : \"array\",\n"
				+ "                            \"items\" : \"double\"\n" + "                        }},\n"
				+ "                        {\"name\": \"channel\", \"type\": \"int\"},\n"
				+ "                        {\"name\": \"freq_MHz\", \"type\": \"int\"}\n" + "                    ]\n" + "                }\n"
				+ "        }}\n" + "    ]\n" + "}";

		byte[] avroByteArray = fromJsonToAvro(json, schemastr);

        GenericRecord result = getGenericRecord(schemastr, avroByteArray);

		System.out.println(result.get("timestamp_ms"));
		System.out.println(result.get("mac").toString());
		System.out.println(result.get("linkSpeed_Mbps"));
		System.out.println(result.get("connected_BSSID").toString());

		GenericRecord customInfo = (GenericRecord) result.get("customInfo");
		System.out.println(customInfo.get("location"));
		System.out.println(customInfo.get("cafId"));
		System.out.println(customInfo.get("sessionId"));

		GenericData.Array<GenericRecord> networks = (GenericData.Array<GenericRecord>) result.get("networks");
		for (int i=0; i < networks.size();i++) {
			System.out.println(networks.get(i).get("ssid"));
			GenericData.Array<GenericRecord> rssi_dBm = (GenericData.Array<GenericRecord>) networks.get(i).get("rssi_dBm");
			for (int j=0; j < rssi_dBm.size();j++) {
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

		GenericDatumWriter<Object>  w = new GenericDatumWriter<Object>(schema);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Encoder e = EncoderFactory.get().binaryEncoder(outputStream, null);

		w.write(datum, e);
		e.flush();

		return outputStream.toByteArray();
	}
}