package com.zpi.domain.role

import com.zpi.domain.permission.Permission
import spock.lang.Specification

class RoleUT extends Specification {

    def 'should assign permissions to role'() {
        given:
            def permission1 = new Permission("blog:edit")
            def permission2 = new Permission("blog:write")
            def role = new Role("journalist", '', new HashSet<Permission>())
        when:
            role.assign(permission1)
            role.assign(permission2)
        then:
            role.permissions.size() == 2
            role.permissions.any( {it ->
                it.permission == "blog:edit"
            })
            role.permissions.any( {it ->
                it.permission == "blog:write"
            })
    }

    def 'should remove permissions from role'() {
        given:
            def permission1 = new Permission("blog:edit")
            def permission2 = new Permission("blog:write")
            def role = new Role("journalist", '', [permission1, permission2] as Set)
        when:
            role.remove(permission1)
        then:
            role.permissions.size() == 1
            role.permissions[0].permission == "blog:write"

    }
}
