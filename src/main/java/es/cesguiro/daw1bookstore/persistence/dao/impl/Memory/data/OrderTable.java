package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderTable {

    private List<OrderRecord> orderRecordList = new ArrayList<>();

    public OrderTable() {
        orderRecordList.add(new OrderRecord(1, 2, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), 75.05f, 4));
        orderRecordList.add(new OrderRecord(2, 2, LocalDate.of(2023, 02, 12), LocalDate.of(2023, 02, 15), 190.00f, 4));
        orderRecordList.add(new OrderRecord(3, 3, LocalDate.of(2024, 03, 20), LocalDate.of(2024, 03, 25), 25.75f, 4));
        orderRecordList.add(new OrderRecord(4, 3, LocalDate.of(2024, 03, 25), null, 50.05f, 3));
        orderRecordList.add(new OrderRecord(5, 2, LocalDate.of(2024, 03, 29), null, 125.50f, 2));
        orderRecordList.add(new OrderRecord(6, 3, LocalDate.of(2024, 04, 01), null, 75.25f, 1));
    }

    public List<OrderRecord> selectByUserId(int userId) {
        List<OrderRecord> orderRecordListByUserId = new ArrayList<>();
        for (OrderRecord orderRecord : orderRecordList) {
            if (orderRecord.getUserId() == userId) {
                orderRecordListByUserId.add(orderRecord);
            }
        }
        return orderRecordListByUserId;
    }

}
