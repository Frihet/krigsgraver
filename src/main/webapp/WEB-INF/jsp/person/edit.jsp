<%@ include file="../header.jsp"%>
<%@ include file="../includes.jsp"%>

<div class="container">
    <div>
        <form:form modelAttribute="person" method="post">
            <form:hidden path="id"/>

            <fieldset>
                <legend><fmt:message key="person.title"/></legend>

                <table>
                    <tr>
                        <th><form:label for="westernName.first" path="westernName.first" cssErrorClass="error"><fmt:message key="person.westernName"/></form:label></th>
                        <td><form:input path="westernName.first" /> </td>

                        <td><form:input path="westernName.last" /> </td>

                        <th><form:label for="westernName.father" path="westernName.father" cssErrorClass="error"><fmt:message key="name.father"/></form:label></th>
                        <td><form:input path="westernName.father" /> </td>

                        <form:errors element="td" cssClass="error" path="westernName.*" />
                    </tr>

                    <tr>
                        <th><form:label for="cyrillicName.first" path="cyrillicName.first" cssErrorClass="error"><fmt:message key="person.cyrillicName"/></form:label></th>
                        <td><form:input path="cyrillicName.first" /> </td>

                        <td><form:input path="cyrillicName.last" /> </td>

                        <th><form:label for="cyrillicName.father" path="cyrillicName.father" cssErrorClass="error"><fmt:message key="name.father"/></form:label></th>
                        <td><form:input path="cyrillicName.father" /> </td>

                        <form:errors element="td" cssClass="error" path="cyrillicName.*" />
                    </tr>

                    <tr>
                        <th><form:label for="dateOfBirth" path="dateOfBirth" cssErrorClass="error"><fmt:message key="person.dateOfBirth"/></form:label></th>
                        <td>
                            <form:input path="dateOfBirth.day" size="1" maxlength="2" />
                            <form:input path="dateOfBirth.month" size="1" maxlength="2" />
                            <form:input path="dateOfBirth.year" size="4" maxlength="4" />
                        </td>
                        <form:errors element="td" cssClass="error" path="dateOfBirth.*" />
                    </tr>

                    <tr>
                        <th><form:label for="placeOfBirth" path="placeOfBirth" cssErrorClass="error"><fmt:message key="person.placeOfBirth"/></form:label></th>
                        <td><form:input path="placeOfBirth" /> </td>
                        <form:errors element="td" cssClass="error" path="placeOfBirth" />
                    </tr>

                    <tr>
                        <th><form:label for="nationality" path="nationality" cssErrorClass="error"><fmt:message key="person.nationality"/></form:label></th>
                        <td><form:input path="nationality" /> </td>
                        <form:errors element="td" cssClass="error" path="nationality" />
                    </tr>

                    <tr>
                        <th><form:label for="prisonerNumber" path="prisonerNumber" cssErrorClass="error"><fmt:message key="person.prisonerNumber"/></form:label></th>
                        <td><form:input path="prisonerNumber" /></td>
                        <form:errors element="td" cssClass="error" path="prisonerNumber" />
                    </tr>
                    
                    <tr>
                        <th><form:label for="rank" path="rank" cssErrorClass="error"><fmt:message key="person.rank"/></form:label></th>
                        <td><form:input path="rank" /></td>
                        <form:errors element="td" cssClass="error" path="rank" />
                    </tr>

                    <tr>
                        <th><form:label for="obdNumber" path="obdNumber" cssErrorClass="error"><fmt:message key="person.obdNumber"/></form:label></th>
                        <td><form:input path="obdNumber" /></td>
                        <form:errors element="td" cssClass="error" path="obdNumber" />
                    </tr>

                    <tr>
                        <th><form:label for="dateOfDeath" path="dateOfDeath" cssErrorClass="error"><fmt:message key="person.dateOfDeath"/></form:label></th>
                        <td>
                            <form:input path="dateOfDeath.day" size="1" maxlength="2" />
                            <form:input path="dateOfDeath.month" size="1" maxlength="2" />
                            <form:input path="dateOfDeath.year" size="4" maxlength="4" />
                        </td>
                        <form:errors element="td" cssClass="error" path="dateOfDeath.*" />
                    </tr>

                    <tr>
                        <th><form:label for="placeOfDeath" path="placeOfDeath" cssErrorClass="error"><fmt:message key="person.placeOfDeath"/></form:label></th>
                        <td><form:input path="placeOfDeath" /></td>
                        <form:errors element="td" cssClass="error" path="placeOfDeath" />
                    </tr>
                    
                    <tr>
                        <th><form:label for="causeOfDeath" path="causeOfDeath" cssErrorClass="error"><fmt:message key="person.causeOfDeath"/></form:label></th>
                        <td><form:input path="causeOfDeath" /></td>
                        <form:errors element="td" cssClass="error" path="causeOfDeath" />
                    </tr>

                    <tr>
                        <th><form:label for="remarks" path="remarks" cssErrorClass="error"><fmt:message key="person.remarks"/></form:label></th>
                        <td><form:textarea path="remarks" /></td>
                        <form:errors element="td" cssClass="error" path="remarks" />
                    </tr>
                </table>

<%--
    <form:select path="receiver" cssStyle="width: 25em;" items="${receivers}" itemLabel="name" />
 --%>

                <p>
                    <button type="submit"><fmt:message key="button.save"/></button>
                </p>
            </fieldset>
        </form:form>
    </div>
</div>


<%@ include file="../footer.jsp" %>