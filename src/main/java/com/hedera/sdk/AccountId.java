package com.hedera.sdk;

import com.google.common.base.Splitter;
import com.hedera.sdk.proto.AccountID;
import com.hedera.sdk.proto.AccountIDOrBuilder;

import java.util.Objects;

public final class AccountId implements Entity {
    final AccountID.Builder inner;

    /** Constructs an `AccountId` with `0` for `shard` and `realm` (e.g., `0.0.<accountNum>`). */
    public AccountId(long accountNum) {
        this(0, 0, accountNum);
    }

    public AccountId(long shardNum, long realmNum, long accountNum) {
        inner = AccountID.newBuilder()
            .setRealmNum(realmNum)
            .setShardNum(shardNum)
            .setAccountNum(accountNum);
    }

    /** Constructs an `AccountId` from a string formatted as <shardNum>.<realmNum>.<accountNum> */
    public static AccountId fromString(String account) throws IllegalArgumentException {
        var rawNums = Splitter.on('.')
            .split(account)
            .iterator();

        var newAccount = AccountID.newBuilder();

        try {
            newAccount.setRealmNum(Integer.parseInt(rawNums.next()));
            newAccount.setShardNum(Integer.parseInt(rawNums.next()));
            newAccount.setAccountNum(Integer.parseInt(rawNums.next()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Id format, should be in format {shardNum}.{realmNum}.{accountNum}");
        }

        return new AccountId(newAccount);
    }

    public static AccountId fromProto(AccountIDOrBuilder accountID) {
        return new AccountId(accountID.getShardNum(), accountID.getRealmNum(), accountID.getAccountNum());
    }

    AccountId(AccountID.Builder inner) {
        this.inner = inner;
    }

    public long getShardNum() {
        return inner.getShardNum();
    }

    public long getRealmNum() {
        return inner.getRealmNum();
    }

    public long getAccountNum() {
        return inner.getAccountNum();
    }

    @Override
    public String toString() {
        return String.format("%d.%d.%d", getShardNum(), getRealmNum(), getAccountNum());
    }

    public AccountID toProto() {
        return inner.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        var other = (AccountId) o;
        return other.getAccountNum() == getAccountNum() && other.getRealmNum() == getRealmNum()
                && other.getShardNum() == this.getShardNum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNum(), getRealmNum(), getShardNum());
    }
}