package com.consultadd.service;

import com.consultadd.dto.AccessKey;
import com.consultadd.dto.IAMUserResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IAMService {

    private final IamClient iam;
    private final Logger LOGGER = LoggerFactory.getLogger(IAMService.class);

    //List all IAM users
    public List<IAMUserResponse> listAllUsers() throws ExecutionException, InterruptedException {
        List<User> users = new ArrayList<>();
        try {
            boolean done = false;
            String newMarker = null;

            while (!done) {
                ListUsersResponse response;

                if (newMarker == null) {
                    ListUsersRequest request = ListUsersRequest.builder().build();
                    response = iam.listUsers(request);
                } else {
                    ListUsersRequest request = ListUsersRequest.builder().marker(newMarker).build();
                    response = iam.listUsers(request);
                }
                users.addAll(response.users());

                if (!response.isTruncated()) {
                    done = true;
                } else {
                    newMarker = response.marker();
                }
            }
        } catch (IamException e) {
            LOGGER.error("Error encountered while fetching users : {}", e.getMessage());
        }
        for (User user : users) {
            LOGGER.info(user.toString());
        }
        return convertToResponse(users);
    }

    //Fetch Access keys for IAM User
    public List<AccessKey> listAccessKeys(String username) {
        List<AccessKeyMetadata> awsResponse = new ArrayList<>();
        try {
            boolean done = false;
            String newMarker = null;
            while (!done) {
                ListAccessKeysResponse response;
                if (newMarker == null) {
                    ListAccessKeysRequest request = ListAccessKeysRequest.builder()
                            .userName(username)
//                            .maxItems()
                            .build();
                    response = iam.listAccessKeys(request);
                } else {
                    ListAccessKeysRequest request = ListAccessKeysRequest.builder()
                            .userName(username)
                            .marker(newMarker).build();
                    response = iam.listAccessKeys(request);
                }
                awsResponse.addAll(response.accessKeyMetadata());
                if (!response.isTruncated()) {
                    done = true;
                } else {
                    newMarker = response.marker();
                }
            }
        } catch (IamException e) {
            LOGGER.error("An error encountered while fetch Access Keys : {}", e.getMessage());
        }

        List<AccessKey> res = new ArrayList<>(awsResponse.size());
        awsResponse.forEach(accessKeyMetadata -> res.add(AccessKey.builder().accessKeyId(accessKeyMetadata.accessKeyId())
                .status(accessKeyMetadata.statusAsString()).createDate(accessKeyMetadata.createDate())
                .build()));
        return res;
    }

    private List<IAMUserResponse> convertToResponse(List<User> users) throws ExecutionException, InterruptedException {
        List<IAMUserResponse> res = new ArrayList<>(users.size());
        for (User user : users) {
//            var accessKey = ListAccessKeysRequest.builder().userName(user.userName()).build();
            CompletableFuture<List<AccessKey>> accessKeysFuture = CompletableFuture.supplyAsync(() -> this.listAccessKeys(user.userName()));
            Map<String, String> tags = user.tags().stream().collect(Collectors.toMap(Tag::key, Tag::value));
            Map<String, String> permissionsBoundary = user.permissionsBoundary() != null ? Map.
                    of("permissionsBoundaryType", user.permissionsBoundary().permissionsBoundaryTypeAsString(),
                            "permissionsBoundaryArn", user.permissionsBoundary().permissionsBoundaryArn()) :
                    Map.of("permissionsBoundaryType", "", "permissionsBoundaryArn", "");
            List<AccessKey> accessKeys = accessKeysFuture.get();
            res.add(IAMUserResponse.builder().accessKeys(accessKeys).userId(user.userId()).userName(user.userName()).path(user.path())
                    .createDate(user.createDate()).arn(user.arn()).passwordLastUsed(user.passwordLastUsed()).tags(tags)
                    .permissionsBoundary(permissionsBoundary).build());
        }
        return res;
    }
}
