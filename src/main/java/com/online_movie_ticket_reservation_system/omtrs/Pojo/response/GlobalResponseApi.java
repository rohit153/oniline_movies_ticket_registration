package com.online_movie_ticket_reservation_system.omtrs.Pojo.response;

import com.online_movie_ticket_reservation_system.omtrs.Enum.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rohit  Tamang
 * on 10 Dec, 2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalResponseApi implements Serializable {

    private ResponseStatus status;
    private String message;
    private Date dateTime;
    private Object data;


    public void setResponse(String message, ResponseStatus status, Object data, Date date) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.dateTime = date;
    }


    protected GlobalResponseApi successResponse(String message, Object data) {
        GlobalResponseApi globalApiResponse = new GlobalResponseApi();
        globalApiResponse.setStatus(ResponseStatus.SUCCESS);
        globalApiResponse.setDateTime(new Date());
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(data);
        return globalApiResponse;
    }

    protected GlobalResponseApi errorResponse(String message, Object errors) {
        GlobalResponseApi globalApiResponse = new GlobalResponseApi();
        globalApiResponse.setStatus(ResponseStatus.FAIL);
        globalApiResponse.setMessage(message);
        globalApiResponse.setDateTime(new Date());
        globalApiResponse.setData(errors);
        return globalApiResponse;
    }


}
