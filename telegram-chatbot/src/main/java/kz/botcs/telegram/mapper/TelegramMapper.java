package kz.botcs.telegram.mapper;

import kz.botcs.chatbot.InMessage;
import kz.botcs.chatbot.outmessage.BottomMenuOutMessage;
import kz.botcs.chatbot.outmessage.TextOutMessage;
import kz.botcs.telegram.dto.in.InUpdate;
import kz.botcs.telegram.dto.out.OutTextMessage;
import kz.botcs.telegram.dto.out.OutPhotoMessage;
import kz.botcs.telegram.dto.out.OutReplyKeyboardMarkup;
import kz.botcs.telegram.dto.out.OutWebhook;

public interface TelegramMapper {
    InMessage toInMessage(InUpdate update);

    OutTextMessage toMessageTo(
            Integer userId, TextOutMessage textOutMessage,
            OutReplyKeyboardMarkup replyKeyboardMarkup);


    OutPhotoMessage toPhoto(
            Integer userId, TextOutMessage textOutMessage,
            OutReplyKeyboardMarkup replyKeyboardMarkup);

    OutReplyKeyboardMarkup toReplyKeyboardMarkup(BottomMenuOutMessage outMessage);

    OutWebhook toWebhook(String webhookUrl);
}
