package com.hedera.sdk;

import com.hedera.sdk.crypto.Key;
import com.hedera.sdk.proto.ContractID;
import com.hedera.sdk.proto.ContractIDOrBuilder;

import java.util.Objects;

public final class ContractId implements Key, Entity {
    private final ContractID.Builder inner;

    public ContractId(long shardNum, long realmNum, long contractNum) {
        inner = ContractID.newBuilder()
            .setShardNum(shardNum)
            .setRealmNum(realmNum)
            .setContractNum(contractNum);
    }

    public ContractId(ContractIDOrBuilder contractID) {
        this(contractID.getShardNum(), contractID.getRealmNum(), contractID.getContractNum());
    }

    public long getShardNum() {
        return inner.getShardNum();
    }

    public long getRealmNum() {
        return inner.getRealmNum();
    }

    public long getContractNum() {
        return inner.getContractNum();
    }

    @Override
    public com.hedera.sdk.proto.Key toKeyProto() {
        return com.hedera.sdk.proto.Key.newBuilder()
            .setContractID(inner)
            .build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShardNum(), getRealmNum(), getContractNum());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof ContractId))
            return false;
        var contractId = (ContractId) other;

        return getShardNum() == contractId.getShardNum() && getRealmNum() == contractId.getRealmNum()
                && getContractNum() == contractId.getContractNum();
    }

    public ContractID toProto() {
        return inner.build();
    }
}
