package com.hedera.hashgraph.sdk;

import com.google.protobuf.ByteString;
import com.hedera.hashgraph.sdk.proto.ConsensusSubmitMessageTransactionBody;
import com.hedera.hashgraph.sdk.proto.TransactionBody;

public final class MessageSubmitTransaction extends TransactionBuilder<MessageSubmitTransaction> {
    private final ConsensusSubmitMessageTransactionBody.Builder builder;

    public MessageSubmitTransaction() {
        builder = ConsensusSubmitMessageTransactionBody.newBuilder();
    }

    /**
     * Set the topic ID to submit the message to.
     *
     * @return {@code this}.
     */
    public MessageSubmitTransaction setTopicId(TopicId topicId) {
        builder.setTopicID(topicId.toProtobuf());
        return this;
    }

    /**
     * Set the message to submit, as a UTF-8 string.
     *
     * @return {@code this}.
     */
    public MessageSubmitTransaction setMessage(String message) {
        builder.setMessage(ByteString.copyFromUtf8(message));
        return this;
    }

    /**
     * Set the message to submit, as a byte array.
     *
     * @return {@code this}.
     */
    public MessageSubmitTransaction setMessage(byte[] message) {
        builder.setMessage(ByteString.copyFrom(message));
        return this;
    }

    @Override
    void onBuild(TransactionBody.Builder bodyBuilder) {
        bodyBuilder.setConsensusSubmitMessage(builder);
    }
}