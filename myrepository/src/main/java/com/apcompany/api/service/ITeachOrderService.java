package com.apcompany.api.service;

import com.apcompany.api.model.form.OrderTeacherScoreForm;
import com.apcompany.api.model.schema.BookTimeTeachDO;
import com.apcompany.api.model.schema.InvitationTeachDO;
import com.apcompany.api.model.schema.TeachOrderDO;

public interface ITeachOrderService {
	
	TeachOrderDO createTeachOrder(InvitationTeachDO invitationTeachDO);
	
	boolean markScoreForOrder(int studentId,OrderTeacherScoreForm form);
	
	TeachOrderDO getById(int id);
	
	//book order
	TeachOrderDO createBookOrder(BookTimeTeachDO bookTimeTeachDO);
	
	TeachOrderDO cancelBookOrder(int bookId);

}
