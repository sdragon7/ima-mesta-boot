package com.imamesta.services;

import com.imamesta.domain.MyCheck;
import com.imamesta.domain.orders.ActiveOrder;

public interface MyCheckService {

	MyCheck saveMyCheck(MyCheck myCheck, ActiveOrder order);
}
