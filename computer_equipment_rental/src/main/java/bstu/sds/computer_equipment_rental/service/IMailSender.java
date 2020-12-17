package bstu.sds.computer_equipment_rental.service;

import bstu.sds.computer_equipment_rental.excaption.ServiceException;

public interface IMailSender {
    void send(String emailTo, String subject, String message)throws ServiceException;
}
