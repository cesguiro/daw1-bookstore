package es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data;

import es.cesguiro.daw1bookstore.persistence.dao.impl.Memory.data.record.OrderRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderTable {

    private List<OrderRecord> orderRecordList = new ArrayList<>();

    public OrderTable() {
        orderRecordList.add(new OrderRecord(1, 2, null, null, null, 0));
        orderRecordList.add(new OrderRecord(2, 3, null, null, null, 0));
        orderRecordList.add(new OrderRecord(3, 2, LocalDate.of(2023, 11, 30), LocalDate.of(2023, 12, 05), new BigDecimal(85.50), 4));
        orderRecordList.add(new OrderRecord(4, 2, LocalDate.of(2023, 02, 12), LocalDate.of(2023, 02, 15), new BigDecimal(13.20), 4));
        orderRecordList.add(new OrderRecord(5, 3, LocalDate.of(2024, 03, 20), LocalDate.of(2024, 03, 25), new BigDecimal(61.40), 4));
        orderRecordList.add(new OrderRecord(6, 3, LocalDate.of(2024, 03, 25), null, new BigDecimal(49.20), 3));
        orderRecordList.add(new OrderRecord(7, 2, LocalDate.of(2024, 03, 29), null, new BigDecimal(138.80), 2));
        orderRecordList.add(new OrderRecord(8, 3, LocalDate.of(2024, 04, 01), null, new BigDecimal(129.50), 1));
    }

    public List<OrderRecord> selectOrderByUserId(int userId) {
        List<OrderRecord> orderRecordListByUserId = new ArrayList<>();
        for (OrderRecord orderRecord : orderRecordList) {
            if (orderRecord.getUserId() == userId && orderRecord.getStatus() != 0) {
                orderRecordListByUserId.add(orderRecord);
            }
        }
        return orderRecordListByUserId;
    }

    public OrderRecord selectById(Integer id) {
        for (OrderRecord orderRecord : orderRecordList) {
            if (orderRecord.getId() == id) {
                return orderRecord;
            }
        }
        return null;
    }
}
