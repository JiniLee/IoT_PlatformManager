package XACML2Bool
import scala.xml._

object Parser{
  //if policyset --> PolicySet.parse()
  //if policy --> Policy.parse()
}

class XACMLParser {
  def printHello = println("Hello!")
  var policy = <Policy xmlns="urn:oasis:names:tc:xacml:3.0:core:schema:wd-17" PolicyId="ConflictFamilyPolicy"
                  RuleCombiningAlgId="urn:oasis:names:tc:xacml:3.0:rule-combining-algorithm:deny-unless-permit" Version="1.0">
    <Target>
      <AnyOf>
        <AllOf>
          <Match MatchId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
            <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">car</AttributeValue>
            <AttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:resource:resource-id"
                                 Category="urn:oasis:names:tc:xacml:3.0:attribute-category:resource"
                                 DataType="http://www.w3.org/2001/XMLSchema#string" MustBePresent="true"/>
          </Match>
        </AllOf>
      </AnyOf>
    </Target>
    <Rule RuleId="permit-rule" Effect="Permit">

      <Condition>
        <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-is-in">
          <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">family</AttributeValue>
          <AttributeDesignator AttributeId="http://selab.hanayng.ac.kr/id/role"
                               Category="urn:oasis:names:tc:xacml:1.0:subject-category:access-subject"
                               DataType="http://www.w3.org/2001/XMLSchema#string" MustBePresent="true"/>
        </Apply>
      </Condition>
      <AdviceExpressions>
        <AdviceExpression AdviceId="permit-time-range-advice2" AppliesTo="Permit">
          <AttributeAssignmentExpression AttributeId="urn:oasis:names:tc:xacml:2.0:example:attribute:text">
            <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">Permit: You can start car if you are the family member.
            </AttributeValue>
          </AttributeAssignmentExpression>
        </AdviceExpression>
      </AdviceExpressions>
    </Rule>
    <AdviceExpressions>
      <AdviceExpression AdviceId="AutoGeneratedPolicyAdviceForFamilyPolicy" AppliesTo="Permit">
        <AttributeAssignmentExpression AttributeId="PolicyId">
          <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">FamilyPolicy</AttributeValue>
        </AttributeAssignmentExpression>
        <AttributeAssignmentExpression AttributeId="RuleCombiningAlgId">
          <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">deny-unless-permit</AttributeValue>
        </AttributeAssignmentExpression>
        <AttributeAssignmentExpression AttributeId="Target">
          <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-equal">
            <AttributeValue DataType="http://www.w3.org/2001/XMLSchema#string">car</AttributeValue>
            <Apply FunctionId="urn:oasis:names:tc:xacml:1.0:function:string-one-and-only">
              <AttributeDesignator AttributeId="urn:oasis:names:tc:xacml:1.0:resource:resource-id" Category="urn:oasis:names:tc:xacml:3.0:attribute-category:resource" DataType="http://www.w3.org/2001/XMLSchema#string" MustBePresent="true"/>
            </Apply>
          </Apply>
        </AttributeAssignmentExpression>
      </AdviceExpression>
    </AdviceExpressions>
  </Policy>


  def printX = {

    var p = policy.toString()
    var q = scala.xml.XML.loadString(p);
    // q = scala.xml.XML.load(url:URL) // URL로 파일에 접근하는 것도 가능한 듯

    // \는 직계 자손중에서 찾고
    // \\는 직계 아닌것도 다 찾음, 한개면 Elem, 여러개면 NodeSeq
    // https://medium.com/@harittweets/working-with-xml-in-scala-bd6271a1e178

    println("--1--")
    println (q.getClass)
    println("--2--")
    println(q \ "Condition")
    println("--3--")
    println(q \\ "Condition")
    println("--4--")
    println(q \\ "Condition" \ "Apply")
    println("--5--")
    println(q \ "Condition" \ "Apply")
    println("--6--")
    println(q \ "AttributeDesignator")
    println("--7--")
    println(q \ "Apply")
    println("--8--")
    println(q \ "Target")

    println("--9--")
    println((q \ "Target") \\ "@AttributeId")

    println("--10--")
    println(q \\ "@PolicyId")

  }



}
