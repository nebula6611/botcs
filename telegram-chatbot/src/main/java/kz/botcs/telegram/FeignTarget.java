package kz.botcs.telegram;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import kz.botcs.telegram.dto.*;

public interface FeignTarget {
    @RequestLine("GET /getUpdates?offset={offset}")
    UpdateResult getUpdates(@Param("offset") Integer offset);

    @RequestLine("POST /sendMessage")
    @Headers("Content-Type: application/json")
    void sendMessage(MessageTo messageTo);

    @RequestLine("POST /editMessageText")
    @Headers("Content-Type: application/json")
    void editMessageText(EditMessage editMessage);

    @RequestLine("POST /answerCallbackQuery")
    @Headers("Content-Type: application/json")
    void answerCallbackQuery(AnswerCallbackQuery answerCallbackQuery);

    @RequestLine("POST /sendPhoto")
    @Headers("Content-Type: application/json")
    void sendPhoto(Photo photo);

    @RequestLine("POST /setMyCommands")
    @Headers("Content-Type: application/json")
    void setMyCommands(BotCommands botCommands);

    @RequestLine("POST /setWebhook")
    @Headers("Content-Type: application/json")
    void setWebhook(Webhook webhook);

    @RequestLine("POST /deleteWebhook")
    @Headers("Content-Type: application/json")
    void deleteWebhook();

}
