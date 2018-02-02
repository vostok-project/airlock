import ru.kontur.airlock.dto.AirlockMessage;
import ru.kontur.airlock.dto.EventGroup;
import ru.kontur.airlock.dto.EventRecord;

import java.util.ArrayList;

class AirlockMessageGenerator {

    static AirlockMessage generateAirlockMessage() {
        return generateAirlockMessage("routing-key");
    }

    static AirlockMessage generateAirlockMessage(String routingKey) {
        long ts = System.currentTimeMillis();
        AirlockMessage message = new AirlockMessage();
        for (int i = 1; i <= 3; i++) {
            EventGroup eventGroup = new EventGroup();
            eventGroup.eventRoutingKey = routingKey + "." + Integer.toString(i);
            eventGroup.eventRecords = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                EventRecord eventRecord = new EventRecord();
                eventRecord.timestamp = ts++;
                eventRecord.data = new byte[10];
                for (int k = 0; k < 10; k++) {
                    eventRecord.data[k] = (byte) ((i + j + k) % 256);
                }
                eventGroup.eventRecords.add(eventRecord);
            }
            message.eventGroups.add(eventGroup);
        }
        return message;
    }
}
