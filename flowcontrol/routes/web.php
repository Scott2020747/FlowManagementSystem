<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', 'MainController@index')->name('main');
Route::get('/configurations', 'MainController@configurations')->name('configurations');
Route::post('/configurations/save', 'MainController@configurationssave')->name('configurationssave');
Route::get('/xml', 'MainController@grab_xml')->name('grabxml');
Route::get('/xml/grabreservedtimeslot', 'MainController@grabreservedtimeslot')->name('grabreservedtimeslot');
Route::post('/actions/moveflighttomanual', 'MainController@moveflighttomanual')->name('moveflighttomanual');
Route::post('/actions/reservetimeslot', 'MainController@reservetimeslot')->name('reservetimeslot');
