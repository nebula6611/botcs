package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.in.Update;
import kz.botcs.telegram.dto.out.TextMessage;
import kz.botcs.telegram.dto.out.PhotoMessage;
import kz.botcs.telegram.dto.out.ReplyKeyboardMarkup;
import kz.botcs.telegram.dto.out.Webhook;

public interface TelegramMapper {
    InMessage toInMessage(Update update);

    TextMessage toMessageTo(Integer userIdInt, TextOutMessage outMessage);

    PhotoMessage toPhoto(Integer userId, TextOutMessage textOutMessage);

    ReplyKeyboardMarkup toReplyKeyboardMarkup(BottomMenuOutMessage outMessage);

    Webhook toWebhook(String webhookUrl);
}
