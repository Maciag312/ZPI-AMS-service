package com.zpi.domain.group

import com.zpi.domain.group.rule.NumericMatcher
import com.zpi.domain.group.rule.Rule
import com.zpi.domain.group.rule.StringMatcher
import com.zpi.domain.permission.Permission
import com.zpi.domain.user.User
import com.zpi.infrastructure.persistance.group.InMemoryGroupRepository
import com.zpi.infrastructure.persistance.permission.InMemoryPermissionRepository
import spock.lang.Specification
import spock.lang.Subject

class GroupServiceUT extends Specification {

    def repo = new InMemoryGroupRepository()
    def permissionRepo = new InMemoryPermissionRepository()

    @Subject
    def service = new GroupServiceImpl(repo, permissionRepo);

    def 'should assign permissions to a group'() {
        given:
            def permission1 = new Permission("blog:edit")
            def permission2 = new Permission("blog:write")
            permissionRepo.save(permission1)
            permissionRepo.save(permission2)
            service.add("journalists", "", new HashSet<Permission>())

        when:
            service.assignPermissionToGroup("journalists", "blog:edit")
            service.assignPermissionToGroup("journalists", "blog:write")

        then:
            def group = service.getAll()[0]
            group.name == "journalists"
            group.permissions.any({
                it.permission == "blog:edit"
            })
            group.permissions.any({
                it.permission == "blog:write"
            })
    }

    def 'should remove permissions from a group'() {
        given:
            def permission1 = new Permission("blog:edit")
            def permission2 = new Permission("blog:write")
            permissionRepo.save(permission1)
            permissionRepo.save(permission2)
            service.add("journalists", "", [permission1, permission2] as Set)
            service

        when:
            service.removePermissionFromGroup("journalists", "blog:write")

        then:
            def group = service.getAll()[0]
            group.name == "journalists"
            group.permissions.size() == 1
            group.permissions.any({
                it.permission == "blog:edit"
            })
    }

    def 'should throw trying to remove permissions from a group which doesnt exist'() {
        given:
            def permission1 = new Permission("blog:edit")
            def permission2 = new Permission("blog:write")
            permissionRepo.save(permission1)
            permissionRepo.save(permission2)

        when:
            service.removePermissionFromGroup("journalists", "blog:write")

        then:
            thrown IllegalArgumentException
    }

    def 'should throw trying to assign permissions from a group which doesnt exist'() {
        given:
            def permission1 = new Permission("blog:edit")
            def permission2 = new Permission("blog:write")
            permissionRepo.save(permission1)
            permissionRepo.save(permission2)

        when:
            service.assignPermissionToGroup("journalists", "blog:write")

        then:
            thrown IllegalArgumentException
    }

    def 'should throw trying to assign permissions which doesnt exist'() {
        given:
            service.add("journalists", "", new HashSet<Permission>())

        when:
            service.assignPermissionToGroup("journalists", "blog:write")

        then:
            thrown IllegalArgumentException
    }

    def 'should get groups for user that satisfies groups rules'() {
        given:
            def shouldBeAdultMatcher = new NumericMatcher('age', 18, NumericMatcher.Operator.GREATER_OR_EQUAL)
            def shouldSpeakFinish = new StringMatcher('languages', 'finish', StringMatcher.Operator.CONTAINS)
            def user = new User('john', "zpi@test.com", true, "", ['languages' : 'polish, finish', 'age': '32'])
            def rule = new Rule('is finish and is adult', [shouldBeAdultMatcher, shouldSpeakFinish])
            service.add('journalists', '', [] as Set )
            service.addRule('journalists', rule)

        when:
            def groups = service.getAllForUser(user)
        then:
            groups[0].name == "journalists"
    }

    def 'should not get groups for user that does not satisfy groups rules'() {
        given:
            def shouldBeAdultMatcher = new NumericMatcher('age', 18, NumericMatcher.Operator.GREATER_OR_EQUAL)
            def shouldSpeakFinish = new StringMatcher('languages', 'finish', StringMatcher.Operator.CONTAINS)
            def user = new User('john', "zpi@test.com", true, "", ['languages' : 'polish, finish', 'age': '16'])
            def rule = new Rule('is finish and is adult', [shouldBeAdultMatcher, shouldSpeakFinish])
            service.add('journalists', '', [] as Set )
            service.addRule('journalists', rule)

        when:
            def groups = service.getAllForUser(user)
        then:
            groups.empty
    }
}
