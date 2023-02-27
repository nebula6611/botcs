package kz.botcs.telegram;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import kz.botcs.telegram.dto.in.InUpdateResult;
import kz.botcs.telegram.dto.out.*;

public interface FeignTarget {
    @RequestLine("GET /getUpdates?offset={offset}")
    InUpdateResult getUpdates(@Param("offset") Integer offset);

    @RequestLine("POST /sendMessage")
    @Headers("Content-Type: application/json")
    void sendMessage(OutTextMessage textMessage);

    @RequestLine("POST /editMessageText")
    @Headers("Content-Type: application/json")
    void editMessageText(OutTextMessage textMessage);

    @RequestLine("POST /sendPhoto")
    @Headers("Content-Type: application/json")
    void sendPhoto(OutPhotoMessage photoMessage);

    @RequestLine("POST /editMessageCaption")
    @Headers("Content-Type: application/json")
    void editMessageCaption(OutPhotoMessage photoMessage);

    @RequestLine("POST /answerCallbackQuery")
    @Headers("Content-Type: application/json")
    void answerCallbackQuery(OutAnswerCallbackQuery answerCallbackQuery);

    @RequestLine("POST /setWebhook")
    @Headers("Content-Type: application/json")
    void setWebhook(OutWebhook webhook);

    @RequestLine("POST /deleteWebhook")
    @Headers("Content-Type: application/json")
    void deleteWebhook();

}
