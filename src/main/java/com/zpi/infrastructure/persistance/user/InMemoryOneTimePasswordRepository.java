package com.zpi.infrastructure.persistance.user;

import com.zpi.domain.user.OneTimePassword;
import com.zpi.domain.user.OneTimePasswordRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.*;

@Repository
public class InMemoryOneTimePasswordRepository implements OneTimePasswordRepository {

    HashMap<Pair<String, Long>, OneTimePasswordTuple> oneTimePasswordTupleMap = new HashMap<>();

    @Override
    public void save(OneTimePassword oneTimePassword) {
        oneTimePasswordTupleMap.put(
                new ImmutablePair<>(oneTimePassword.getEmail(), oneTimePassword.getExpiresAt().toEpochSecond(UTC)),
                OneTimePasswordTuple.fromDomain(oneTimePassword));
    }

    @Override
    public List<OneTimePassword> findBy(String email) {
        return oneTimePasswordTupleMap.values().stream()
                .filter(otp->otp.getEmail().equals(email))
                .map(OneTimePasswordTuple::toDomain)
                .collect(Collectors.toList());
    }
}
