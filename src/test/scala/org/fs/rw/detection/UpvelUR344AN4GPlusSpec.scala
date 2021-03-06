package org.fs.rw.detection

import java.io.File

import scala.io.Source

import org.fs.rw.domain.AnnexMode
import org.fs.rw.domain.Modulation
import org.fs.rw.domain.RouterInfo
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UpvelUR344AN4GPlusSpec
    extends FlatSpec {

  val instance = UpvelUR344AN4GPlus

  behavior of "Upvel UR344AN4G+ detector, firmware v1"

  // FIXME: Improve tests
  it should "parse the up state" in {
    val content = Source.fromFile(new File(routerFolder, "v1-up.htm")).mkString
    val parsed = instance.parseContent(content).asInstanceOf[RouterInfo]
    assert(parsed.firmwareOption === Some("Upvel V1"))
    assert(parsed.lineUpOption === Some(true))
    assert(parsed.serverIpOption === Some("10.3.22.38"))
    assert(parsed.modulationOption === Some(Modulation.ADSL2PLUS))
    assert(parsed.annexModeOption === Some(AnnexMode.A))
    assert(parsed.downstream.snrMarginOption === Some(6.8))
    assert(parsed.downstream.lineAttenuationOption === Some(24.7))
    assert(parsed.downstream.dataRateOption === Some(8191))
    assert(parsed.downstream.crcErrorsOption === None)
    assert(parsed.downstream.erroredSecondsOption === Some(62171))
    assert(parsed.downstream.severelyErroredSecondsOption === Some(60151))
    assert(parsed.upstream.snrMarginOption === Some(9.1))
    assert(parsed.upstream.lineAttenuationOption === Some(14.1))
    assert(parsed.upstream.dataRateOption === Some(599))
    assert(parsed.upstream.crcErrorsOption === None)
    assert(parsed.upstream.erroredSecondsOption === Some(18))
    assert(parsed.upstream.severelyErroredSecondsOption === Some(0))
    assert(parsed.unavailableSecondsOption === Some(288))
  }

  it should "parse the up/disconnected state" in {
    val content = Source.fromFile(new File(routerFolder, "v1-up-disconnected.htm")).mkString
    val parsed = instance.parseContent(content).asInstanceOf[RouterInfo]
    assert(parsed.lineUpOption === Some(true))
  }

  it should "parse the wait-for-init state" in {
    val content = Source.fromFile(new File(routerFolder, "v1-wait-for-init.htm")).mkString
    val parsed = instance.parseContent(content).asInstanceOf[RouterInfo]
    assert(parsed.lineUpOption === Some(false))
    assert(parsed.downstream.snrMarginOption === None)
    assert(parsed.downstream.lineAttenuationOption === None)
    assert(parsed.downstream.dataRateOption === None)
    assert(parsed.downstream.crcErrorsOption === None)
    assert(parsed.downstream.erroredSecondsOption === None)
    assert(parsed.downstream.severelyErroredSecondsOption === None)
    assert(parsed.upstream.snrMarginOption === None)
    assert(parsed.upstream.lineAttenuationOption === None)
    assert(parsed.upstream.dataRateOption === None)
    assert(parsed.upstream.crcErrorsOption === None)
    assert(parsed.upstream.erroredSecondsOption === None)
    assert(parsed.upstream.severelyErroredSecondsOption === None)
    assert(parsed.unavailableSecondsOption === None)
  }

  behavior of "Upvel UR344AN4G+ detector, firmware v1.782"

  it should "parse the up state" in {
    val content = Source.fromFile(new File(routerFolder, "v1.782-up.htm")).mkString
    val parsed = instance.parseContent(content).asInstanceOf[RouterInfo]
    assert(parsed.firmwareOption === Some("Upvel V1.782"))
    assert(parsed.lineUpOption === Some(true))
    assert(parsed.serverIpOption === Some("89.236.238.194"))
    assert(parsed.modulationOption === Some(Modulation.ADSL2PLUS))
    assert(parsed.annexModeOption === Some(AnnexMode.A))
    assert(parsed.downstream.snrMarginOption === Some(15.0))
    assert(parsed.downstream.lineAttenuationOption === Some(20.5))
    assert(parsed.downstream.dataRateOption === Some(2046))
    assert(parsed.downstream.crcErrorsOption === None)
    assert(parsed.downstream.erroredSecondsOption === Some(1111))
    assert(parsed.downstream.severelyErroredSecondsOption === Some(3333))
    assert(parsed.upstream.snrMarginOption === Some(9.0))
    assert(parsed.upstream.lineAttenuationOption === Some(11.1))
    assert(parsed.upstream.dataRateOption === Some(636))
    assert(parsed.upstream.crcErrorsOption === None)
    assert(parsed.upstream.erroredSecondsOption === Some(2222))
    assert(parsed.upstream.severelyErroredSecondsOption === Some(4444))
    assert(parsed.unavailableSecondsOption === Some(75))
  }

  val resourcesFolder = new File("src/test/resources")
  val routerFolder = new File(resourcesFolder, "upvel-ur-344an4g+")
}
