/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar =
		[
		    { name: 'document',    items : [ 'Source','-','DocProps','Preview','Print','-','Templates' ] },
		    { name: 'clipboard',   items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		    { name: 'editing',     items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
		    '/',
		    { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		    { name: 'paragraph',   items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		    { name: 'links',       items : [ 'Link','Unlink','Anchor' ] },
		    { name: 'insert',      items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ] },
		    '/',
		    { name: 'styles',      items : [ 'Styles','Format','Font','FontSize' ] },
		    { name: 'colors',      items : [ 'TextColor','BGColor' ] },
		    { name: 'tools',       items : [ 'Maximize', 'ShowBlocks' ] }
		];
	config.font_names =
		'宋体;黑体;微软雅黑;隶书;楷体_GB2312;新宋体;Comic Sans MS;Courier New;'+
	    'Arial/Arial, Helvetica, sans-serif;' +
	    'Times New Roman/Times New Roman, Times, serif;' +
	    'Verdana';
};

