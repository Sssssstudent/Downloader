<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    Authorization page

    ${message!}

    <@l.login "/registration" />
</@c.page>