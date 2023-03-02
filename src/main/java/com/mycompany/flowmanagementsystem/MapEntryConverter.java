package com.mycompany.flowmanagementsystem;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author sbilau
 */
public class MapEntryConverter implements Converter {

    @Override
    public boolean canConvert(Class clazz) {
        return AbstractMap.class.isAssignableFrom(clazz);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

        AbstractMap map = (AbstractMap) value;
        for (Object obj : map.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            writer.startNode(entry.getKey().toString());
            Object val = entry.getValue();
            if (null != val) {
                writer.setValue(val.toString());
            }
            writer.endNode();
        }

    }

    /**
     *
     * @param reader
     * @param context
     * @return
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

        Map<LocalDateTime, String> map = new TreeMap<LocalDateTime, String>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            String str = reader.getNodeName(); // nodeName aka element's name

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime key = LocalDateTime.parse(str, formatter);

            String value = reader.getValue();
            map.put(key, value);

            reader.moveUp();
        }

        return map;
    }

}
