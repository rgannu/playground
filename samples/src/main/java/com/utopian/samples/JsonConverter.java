package com.utopian.samples;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by garamasu on 2015-11-05.
 */
class JsonConverter {
    public static byte[] convertToAvro(byte[] data, Schema schema) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        GenericDatumWriter<Object> writer = new GenericDatumWriter<Object>(schema);
        writer.write(readRecord(data, schema), encoder);
        encoder.flush();
        return outputStream.toByteArray();
    }

    private static GenericData.Record readRecord(byte[] data, Schema schema) throws IOException {
        JsonDecoder decoder = DecoderFactory.get().jsonDecoder(schema, new ByteArrayInputStream(data));
        DatumReader<GenericData.Record> reader = new GenericDatumReader<GenericData.Record>(schema);
        return reader.read(null, decoder);
    }

    public static GenericRecord getGenericRecord(byte[] avro, Schema schema) throws IOException {
        BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(avro, null);
        return new GenericDatumReader<GenericRecord>(schema).read(null, binaryDecoder);
    }

    public static byte[] convertToJson(byte[] avro, Schema schema) throws IOException {
        GenericRecord record = getGenericRecord(avro, schema);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonEncoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema, outputStream);
        new GenericDatumWriter<GenericRecord>(schema).write(record, jsonEncoder);
        jsonEncoder.flush();
        return outputStream.toByteArray();
    }
}
