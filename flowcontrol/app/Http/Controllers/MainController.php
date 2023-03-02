<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class MainController extends Controller
{
    function index() {

        $xmlString = file_get_contents(public_path('run/configs/config.xml'));
        $xmlObject = simplexml_load_string($xmlString);
        $json = json_encode($xmlObject);
        $_phpArray = json_decode($json, true);
        $phpArray = $_phpArray['input'];
        $landingrate = $phpArray['landingrate'];
        $stablesequenceperiod = $phpArray['ssperiod'];
        $defaultflowcontrolposition = $phpArray['defaultflowcontrolposition'];
        $runway = $phpArray['rwy'];

        return view('main', [
            'landingrate' => $landingrate,
            'ssperiod' => $stablesequenceperiod,
            'defaultflowcontrolposition' => $defaultflowcontrolposition,
            'rwy' => $runway,
        ]);
    }

    function configurations() {
        $xmlString = file_get_contents(public_path('run/configs/config.xml'));
        $xmlObject = simplexml_load_string($xmlString);
        $json = json_encode($xmlObject);
        $_phpArray = json_decode($json, true);
	    $phpArray = $_phpArray['input'];

        $landingrate = $phpArray['landingrate'];
        $stablesequenceperiod = $phpArray['ssperiod'];
        $defaultflowcontrolposition = $phpArray['defaultflowcontrolposition'];
        $runway = $phpArray['rwy'];

        return view('configurations', [
            'landingrate' => $landingrate,
            'ssperiod' => $stablesequenceperiod,
            'defaultflowcontrolposition' => $defaultflowcontrolposition,
            'rwy' => $runway,
        ]);
    }

    function grab_xml() {
        $xmlString = file_get_contents(public_path('run/data/sequence_list.xml'));
        $xmlObject = simplexml_load_string($xmlString);
        $json = json_encode($xmlObject);
        $phpArray = json_decode($json, true); 

        return response()->json($phpArray);
    }

    function configurationssave(Request $request) {
        $request->validate([
            'landingrate' => 'required|numeric',
            'stablesequenceperiod' => 'required|numeric',
            'defaultflowcontrolposition' => 'required',
            'runway' => 'required|numeric|in:32,14',
        ]);

        $landingrate = $request->input('landingrate');
        $stablesequenceperiod = $request->input('stablesequenceperiod');
        $defaultflowcontrolposition = $request->input('defaultflowcontrolposition');
        $runway = $request->input('runway');

        $xml = '';
        $xml .= '<config>';
        $xml .= '<input>';
        $xml .= '<landingrate>' . $landingrate . '</landingrate>';
        $xml .= '<ssperiod>' . $stablesequenceperiod . '</ssperiod>';
        $xml .= '<defaultflowcontrolposition>' . $defaultflowcontrolposition . '</defaultflowcontrolposition>';
        $xml .= '<rwy>' . $runway . '</rwy>';
        $xml .= '</input>';
        $xml .= '</config>';

        $sxe = new \SimpleXMLElement($xml);
        $dom = new \DOMDocument('1,0');
        $dom->preserveWhiteSpace = false;
        $dom->formatOutput = true;
        $dom->loadXML($sxe->asXML());

        $dom->save('run/configs/config.xml');

        return redirect('/configurations');
    }

    function moveflighttomanual(Request $request) {
        $request->validate([
            'callsign' => 'required',
            'eta' => 'required',
        ]);

        $callsign = $request->input('callsign');
        $eta = date('Y-m-d\TH:i', strtotime($request->input('eta')));

        $xmlString = file_get_contents(public_path('run/data/sequence_list.xml'));
        $xmlObject = simplexml_load_string($xmlString);
        $json = json_encode($xmlObject);
        $phpArray = json_decode($json, true);

        foreach ($phpArray['Slot'] as $key => $flight) {
            if ($flight['callsign'] == $callsign && $flight['eta'] == $eta) {

                $xml = '';
                $xml .= '<manual>';
                $xml .= '   <manual>';
                $xml .= '       <eta>' . $flight['eta'] . '</eta>';
                $xml .= '       <callsign>' . $flight['callsign'] . '</callsign>';
                $xml .= '       <flightstatus>' . $flight['flightstatus'] . '</flightstatus>';
                $xml .= '       <flightprocedure>' . $flight['flightprocedure'] . '</flightprocedure>';
                $xml .= '       <adep>' . $flight['adep'] . '</adep>';
                $xml .= '       <ades>' . $flight['ades'] . '</ades>';
                $xml .= '       <feederFix>' . $flight['feederFix'] . '</feederFix>';
                $xml .= '       <star>' . $flight['star'] . '</star>';
                $xml .= '       <ac_type>' . $flight['ac_type'] . '</ac_type>';
                $xml .= '       <wtur>' . $flight['wtur'] . '</wtur>';
                $xml .= '       <regis>' . $flight['regis'] . '</regis>';
                $xml .= '       <dof>' . $flight['dof'] . '</dof>';
                $xml .= '       <other_info>' . $flight['other_info'] . '</other_info>';
                $xml .= '       <route>' . $flight['route'] . '</route>';
                $xml .= '       <etd_dof>' . $flight['etd_dof'] . '</etd_dof>';
                $xml .= '       <etd>' . $flight['etd'] . '</etd>';
                $xml .= '       <rfl>' . $flight['rfl'] . '</rfl>';
                $xml .= '       <speed>' . $flight['speed'] . '</speed>';
                $xml .= '       <atd>' . $flight['atd'] . '</atd>';
                $xml .= '       <eobt>' . $flight['eobt'] . '</eobt>';
                $xml .= '       <frule>' . $flight['frule'] . '</frule>';
                $xml .= '   </manual>';
                $xml .= '</manual>';

                $sxe = new \SimpleXMLElement($xml);
                $dom = new \DOMDocument('1,0');
                $dom->preserveWhiteSpace = false;
                $dom->formatOutput = true;
                $dom->loadXML($sxe->asXML());

                $dom->save('run/configs/manual.xml');

                break;
            }
            
        }
        

        // $xml = '';
        // $xml .= '<manual>';
        // $xml .= '   <manual>';
        // $xml .= '       <callsign>' . $callsign . '</callsign>';
        // $xml .= '       <eta>' . $eta . '</eta>';
        // $xml .= '   </manual>';
        // $xml .= '</manual>';

        // $sxe = new \SimpleXMLElement($xml);
        // $dom = new \DOMDocument('1,0');
        // $dom->preserveWhiteSpace = false;
        // $dom->formatOutput = true;
        // $dom->loadXML($sxe->asXML());

        // $dom->save('run/configs/manual.xml');

        return "success";
    }

    function grabreservedtimeslot(Request $request) {
        // Do stuff.
    }

    function reservetimeslot(Request $request) {
        // Do stuff.
        return "Yes";
    }
}
