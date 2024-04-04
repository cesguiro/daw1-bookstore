package es.cesguiro.daw1bookstore.mock.repository;

import es.cesguiro.daw1bookstore.domain.model.OrderDetail;
import es.cesguiro.daw1bookstore.mock.dao.BookDaoMock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDetailRepositoryMock{

    BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();

    private List<OrderDetail> orderDetailList = List.of(
            new OrderDetail(1, bookRepositoryMock.findById(2), 2, new BigDecimal(12.30)),
            new OrderDetail(2, bookRepositoryMock.findById(4), 3, new BigDecimal(20.30)),
            new OrderDetail(3, bookRepositoryMock.findById(1), 1, new BigDecimal(13.20)),
            new OrderDetail(4, bookRepositoryMock.findById(3), 1, new BigDecimal(11.50)),
            new OrderDetail(5, bookRepositoryMock.findById(4), 2, new BigDecimal(20.30)),
            new OrderDetail(6, bookRepositoryMock.findById(5), 1, new BigDecimal(9.30)),
            new OrderDetail(7, bookRepositoryMock.findById(2), 4, new BigDecimal(12.30)),
            new OrderDetail(8, bookRepositoryMock.findById(1), 3, new BigDecimal(11.20)),
            new OrderDetail(9, bookRepositoryMock.findById(3), 2, new BigDecimal(52.60)),
            new OrderDetail(10, bookRepositoryMock.findById(1), 5, new BigDecimal(13.20)),
            new OrderDetail(11, bookRepositoryMock.findById(3), 2, new BigDecimal(11.50)),
            new OrderDetail(12, bookRepositoryMock.findById(4), 3, new BigDecimal(10.40)),
            new OrderDetail(13, bookRepositoryMock.findById(5), 1, new BigDecimal(9.30))
    );

    Map<Integer, List<Integer>> orderOrderDetailMap = Map.of(
            3, List.of(1, 2),
            4, List.of(3),
            5, List.of(4, 5, 6),
            6, List.of(7),
            7, List.of(8, 9),
            8, List.of(10, 11, 12, 13)
    );


    public List<OrderDetail> findByOrderId(Integer orderId) {
        List<OrderDetail> result = new ArrayList<>();
        List<Integer> orderDetailIdList = orderOrderDetailMap.get(orderId);
        for (Integer orderDetailId : orderDetailIdList) {
            result.add(findById(orderDetailId));
        }
        return result;
    }

    private OrderDetail findById(Integer orderDetailId) {
        for (OrderDetail orderDetail : orderDetailList) {
            if (orderDetail.getId() == orderDetailId) {
                return orderDetail;
            }
        }
        return null;
    }
}
