This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

# You can check this video

<?php

// Add oEmebed support for Google Drive Video
wp_embed_register_handler(
	'google-drive-videos',
	'#https://drive.google.com/file/d/1Ax3n2X0evG73Kk_ccsw-DDaEJrq4qmuE/view$#i',
	'pb_add_oembed_handler_dgvideo'
);

function pb_add_oembed_handler_dgvideo( $matches, $attr, $url, $rawattr ) {
	$embed = sprintf(
		'<iframe src="https://drive.google.com/file/d/1Ax3n2X0evG73Kk_ccsw-DDaEJrq4qmuE/view" width="640" height="480"></iframe>',
			esc_attr($matches[1])
	);
	return $embed;
}
