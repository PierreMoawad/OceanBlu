USE ocean_blu;

--  //////// USERS //////// --

INSERT IGNORE INTO user
    (id, username, password, active, first_name, last_name, role, gender, provider)
VALUES (1, 'admin', '$2a$10$BxwiCZxdU6S8xN2wUelMSufY15laFqHqEMgMCUraFFqf7.m.9dcVS', true, 'Pierre', 'Moawad', 'ADMIN',
        'MALE', 'LOCAL');
INSERT IGNORE INTO user
    (id, username, password, active, first_name, last_name, role, gender, provider)
VALUES (2, 'johny', '$2a$10$L4kl2Fq6LtWZLR3uDLc2QeZr8BqD8b0ztAJ7YzeLYIQn8lphs1CQa', true, 'John', 'Smith', 'USER',
        'MALE', 'LOCAL');
INSERT IGNORE INTO user
    (id, username, password, active, first_name, last_name, role, gender, provider)
VALUES (3, 'lana_r', '$2a$10$DTYkfNhl7ZHN2ficqFKv8unCcVlBhaMLD5UOMb5E1qhrGDuLiCmqS', true, 'Lana', 'Robinson', 'USER',
        'FEMALE', 'LOCAL');
INSERT IGNORE INTO user
    (id, username, password, active, first_name, last_name, role, gender, provider)
VALUES (4, 'samantha.schultz', '$2a$10$zyopVv0Re1KJTuK0gEYdduWoAJb/gZaFyeQF7C0SPeA/esolgzbzS', true, 'Samantha',
        'Schultz', 'USER', 'FEMALE', 'LOCAL');

--  //////// PRODUCTS //////// --

INSERT IGNORE INTO product
    (id, name, company, price, quantity, image, description)
VALUES (1000, 'iPhone X', 'Apple', 950, 4, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/iphone_x.jpg'),
        'Finish

•	Space Gray
•	Silver

Capacity1

•	64GB
•	256GB

Size and Weight2

•	Height: 5.65 inches (143.6 mm)
•	Width: 2.79 inches (70.9 mm)
•	Depth: 0.30 inch (7.7 mm)
•	Weight: 6.14 ounces (174 grams)

Display

•	Super Retina HD display
•	5.8-inch (diagonal) all-screen OLED Multi-Touch display
•	HDR display
•	2436-by-1125-pixel resolution at 458 ppi
•	1,000,000:1 contrast ratio (typical)
•	True Tone display
•	Wide color display (P3)
•	3D Touch
•	625 cd/m2 max brightness (typical)
•	Fingerprint-resistant oleophobic coating
•	Support for display of multiple languages and characters simultaneously

The iPhone X display has rounded corners that follow a beautiful curved design,
and these corners are within a standard rectangle.
When measured as a standard rectangular shape,
the screen is 5.85 inches diagonally (actual viewable area is less).

Splash, Water, and Dust Resistant3

•	Rated IP67 under IEC standard 60529

Chip

•	A11 Bionic chip with 64-bit architecture
•	Neural engine
•	Embedded M11 motion coprocessor

Camera

•	12MP wide-angle and telephoto cameras
•	Wide-angle: ƒ/1.8 aperture
•	Telephoto: ƒ/2.4 aperture
•	Optical zoom; digital zoom up to 10x
•	Portrait mode
•	Portrait Lighting (beta)
•	Dual optical image stabilization
•	Six element lens
•	Quad-LED True Tone flash with Slow Sync
•	Panorama (up to 63MP)
•	Sapphire crystal lens cover
•	Backside illumination sensor
•	Hybrid IR filter
•	Autofocus with Focus Pixels
•	Tap to focus with Focus Pixels
•	Live Photos with stabilization
•	Wide color capture for photos and Live Photos
•	Improved local tone mapping
•	Body and face detection
•	Exposure control
•	Noise reduction
•	Auto HDR for photos
•	Auto image stabilization
•	Burst mode
•	Timer mode
•	Photo geotagging
•	Image formats captured: HEIF and JPEG

Video Recording

•	4K video recording at 24 fps, 30 fps, or 60 fps
•	1080p HD video recording at 30 fps or 60 fps
•	720p HD video recording at 30 fps
•	Optical image stabilization for video
•	Optical zoom; 6x digital zoom
•	Quad-LED True Tone flash
•	Slo mo video support for 1080p at 120 fps or 240 fps
•	Time lapse video with stabilization
•	Cinematic video stabilization (1080p and 720p)
•	Continuous autofocus video
•	Body and face detection
•	Noise reduction
•	Take 8MP still photos while recording 4K video
•	Playback zoom
•	Video geotagging
•	Video formats recorded: HEVC and H.264

TrueDepth Camera

•	7MP camera
•	Portrait mode
•	Portrait Lighting (beta)
•	Animoji
•	1080p HD video recording
•	Retina Flash
•	ƒ/2.2 aperture
•	Wide color capture for photos and Live Photos
•	Auto HDR
•	Backside illumination sensor
•	Body and face detection
•	Auto image stabilization
•	Burst mode
•	Exposure control
•	Timer mode

Face ID

•	Enabled by TrueDepth camera for facial recognition

Apple Pay

•	Pay with your iPhone using Face ID in stores, within apps, and on the web
•	Complete purchases made with Apple Pay on your Mac
•	Receive and redeem rewards using rewards cards');
INSERT IGNORE INTO product
    (id, name, company, price, quantity, image, description)
VALUES (1001, 'Galaxy S8', 'Samsung', 850, 3, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/galaxy_s8.jpg'),
        'General

•   Model: Galaxy S8 G950F
•   Released: 21 April, 2017
•   Status: Available

Design

•   Type: Bar
•   Dimensions: 148.9 x 68.1 x 8.0 mm
•   Weight: 155 Grams (With Battery)
•   Waterproof: IP68 Dustproof and Waterproof

Display

•   Display Type: Super AMOLED capacitive touchscreen
•   Size: 5.8 inches
•   Resolution: 1440 x 2960 Pixels, 24 bit color depth
•   Display Colors: 16M Colors
•   Pixel Density: ~ 570 PPI
•   Touch Screen: Yes Multi Touch Support
•   Display Protection: Yes Support
•   Features:   QHD display
                ~570 PPI
                1500:1 contrast ratio
                3D curved glass screen
                Capacitive
                Corning Gorilla Glass 5
                Force Touch
                Multi-touch
                Scratch resistant
                Touch sensitive
                Hardware
•   CPU: Octa -Core 4×2.3GHz Exynos M2 Mongoose + 4x 1.7GHz ARM Cortex-A53
•   GPU: ARM Mali-G71 MP20, 550 MHz, 20-Cores
•   RAM (Memory): 4GB
•   Internal Storage: 64GB
•   Memory Card Slot: microSD, microSDHC, microSDXC
•   Sensors:    Fingerprint
                Accelerometer
                Compass
                Gravity
                Gyroscope
                Hall
                Heart Rate
                Light sensor
                Proximity
                Barometer
                Software
•   Operating System: Android 7.0 Marshmallow Nougat
•   User Interface: Yes with TouchWiz UI

Camera

•   Rear Camera: 12 Megapixels f/2.2 with Dual-LED and AF Rear Camera
•   Image: 4416 x 2761 Pixels
•   Video: 2160p@60fps, 1080p@120fps, 720p@240fps
•   Flash: Yes with Dual-LED Flash
•   Front Camera: 8 Megapixel Front Camera

Network

•   SIM: Nano SIM
•   Dual SIM: Single SIM (Nano)

Connectivity

•   Wi-fi: Wi-Fi 802.11 a/b/g/n/ac, 802.11n 5GHz, Wi-Fi Direct, Wi-Fi Display, Dual Band, 2x2 MiMO
•   USB: Type-C USBv3.1 With OTG, Charging and Mass Storage Function
•   GPS: A-GPS, Beidou, GLONASS, GPS
•   NFC: Yes
•   Headphone Jack: Yes

Battery

•   Capacity: 3,000 mAh
•   Placement: Non-Removable

Media

•   Video Playback: Up to 9 Hours
•   Video Out: Yes
•   FM Radio: Yes Support FM Radio
•   Ring Tones: MP3, WAV ringtones
•   Loudspeaker: Yes

Data

•   4G LTE: LTE : 700 MHz Class 13, LTE : 700 MHz Class 17, LTE : 1700 / 2100 MHz,
            LTE : 800 / 900 / 1900 / 1800 / 2100 / 2600 MHz, LTE-TDD : 1900 MHz (Band 39),
            LTE-TDD : 2300 MHz (Band 40), LTE-TDD : 2500 MHz (Band 41),
            LTE-TDD : 2600 MHz (Band 38), LTE : 700 MHz (Band 28)
•   Speed: LTE Cat 16 1 Gbps Download, 150 Mbps Upload, HSUPA 5.76 Mbps Upload, HSDPA 42 Mbps Download
');
INSERT IGNORE INTO product
    (id, name, company, price, quantity, image, description)
VALUES (1002, 'P20 Pro', 'Huawei', 690, 9, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/huawei_p20.jpg'),
        'General

•   Model:      Huawei P20 Pro
•   Released:   28 March, 2018
•   Status:     Available

Design

•   Type:       Bar
•   Waterproof: No

Display

•   Display Type:       LTPS, IPS, LCD Capacitive touchscreen
•   Size:               5.84-inches FHD+
•   Resolution:         2280 x 1080 Pixels, 24 bit color depth
•   Display Colors:     16M Colors
•   Pixel Density:      ~ 432 PPI Pixel Per Inch
•   Touch Screen:       Yes Multi-Touch Support
•   Display Protection: Yes
•   Features:           IPS, LCD display
                        ~432 PPI Pixel Density
                        Capacitive
                        Multitouch
                        18.5:9 Aspect Ratio
                        Full View Display

Hardware

•   CPU:                Octa-core CPU Clock
•   GPU:                ARM Mali GPU
•   RAM (Memory):       GB/6GB
•   Internal Storage:   64GB/128GB
•   Memory Card Slot:   External Memory Supports Up to 256GB
•   Sensors:            Proximity, Light, Compass, Fingerprint Scanner (Front Mounted),
                        Accelerometer, Gyroscope

Software

•   Operating System:   Android 8.0 Oreo (EMUI 8.0) OS
•   User Interface:     Yes with EMUI 8.0

Camera

•   Rear Camera:        12 Megapixel f/1.7 + 20 Megapixel f/1.7 + 2 Megapixel
•   Image:              5164 x 3873 Pixels
•   Video:              2160@30fps, 1080P@60fps
•   Flash:              Yes with Dual Tone LED Flash
•   Front Camera:       24 Megapixels f/2.0 Aperture, Autofocus

Network

•   SIM:            Nano SIM
•   Dual SIM:       Single Nano SIM or Dual SIM (Nano-SIM + Nano-SIM) Dual Standby

Connectivity

•   Wi-fi:              Wi-Fi 802.11 a/b/g/n/ac, Wi-Fi Display, Wi-Fi Direct, Wi-Fi Hotspot
•   USB:                2.0 USB Type-C 1.0 with Charging and Mass Storage Function
•   GPS:                Yes, with GPS, A-GPS, GLONASS
•   NFC:                Yes
•   Wireless Charging:  No
•   Headphone Jack:     Yes

Battery

•   Capacity:   3500mAh Lithium-Polymer
•   Placement:  Non-Removable

Media

•   Video Playback: Yes Support 3GP, AVI, MKV, ASF, MPEG4, RM, RMVB, WMV
•   Video Out:      Yes With Wireless Media Link
•   FM Radio:       Yes
•   Ring Tones:     Yes
•   Loudspeaker:    Yes
•   Handsfree:      Yes

Data

•   4G LTE: Yes
•   Speed:  FDD-LTE: B1/B2/B3/B4/B5/B7/B8/B9/B12/B17/B18/B19/B20/B26
            TDD-TD-LTE: B38/B39/B40/41
');
INSERT IGNORE INTO product
(id, name, company, price, quantity, image, description)
VALUES (1003, 'Mate 40 Pro', 'Huawei', 775, 6, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/huawei_mate40.jpg'),
        'General

•   Model:      Huawei Mate 40 Pro
•   Released:   October, 2020
•   Status:     Available

Design

•   Type:       Bar
•   Dimensions: 162.9 x 75.5 x 9.1 mm
•   Weight:     212 Grams
•   Waterproof: IP68 dust/water resistant

Display

•   Display Type:   OLED
•   Size:           6.76 inches
•   Resolution:     1344 x 2772 pixels
•   Display Colors: 16M Colors
•   Pixel Density:  456 ppi (pixels per inch)
•   Touch Screen:   Yes
•   Features:       HDR10+
                    90Hz

Hardware

•   CPU:                cta-core (1x3.13 GHz Cortex-A77 + 3x2.54 GHz Cortex-A77 + 4x2.05 GHz Cortex-A55)
•   GPU:                Mali-G78 MP24
•   RAM (Memory):       8 GB
•   Internal Storage:   256 GB
•   Memory Card Slot:   NM (Nano Memory), up to 256GB (uses shared SIM slot)
•   Sensors:            Face ID, fingerprint (under display, optical), accelerometer,
                        gyro, proximity, barometer, compass, color spectrum

Software

•   Operating System:   Android 10 + EMUI 11 (no Google Play Services)
•   User Interface:     Yes

Camera

•   Rear Camera:    50 MP (wide) omnidirectional + 12 MP (periscope telephoto)
                    optical zoom + 20 MP (ultrawide)
•   Image:          2160p
•   Video:          4K@30/60fps, 1080p@30/60/120/240/480fps,
                    720p@960fps, 720p@3840fps, HDR, gyro-EIS
•   Flash:          Leica optics, LED flash, panorama, HDR
•   Front Camera:   13 MP (wide) + TOF 3D (depth/biometrics sensor)

Network

•   SIM:        Nano SIM
•   Dual SIM:   Single SIM (Nano-SIM) or Hybrid Dual SIM (Nano-SIM, dual stand-by)

Connectivity

•   Wi-fi:              Wi-Fi 802.11 a/b/g/n/ac/6, dual-band, Wi-Fi Direct, hotspot
•   USB:                USB Type-C 3.1, USB On-The-Go
•   GPS:                Yes, with dual-band A-GPS, GLONASS, BDS, GALILEO, QZSS, NavIC
•   NFC:                Yes
•   Wireless Charging:  Fast wireless charging 50W + Reverse wireless charging 5W
•   Headphone Jack:     No

Battery

•   Capacity:   Li-Po 4400 mAh + Fast charging 66W + Fast wireless charging
                50W+ Reverse wireless charging 5W
•   Placement:  Non-Removable

Media

•   Video Playback: Yes
•   Video Out:      Yes
•   FM Radio:       No
•   Ring Tones:     Yes
•   Loudspeaker:    Yes
•   Handsfree:      Yes

Data

•   4G LTE:         1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 17, 18, 19,
                    20, 26, 28, 32, 34, 38, 39, 40, 41, 42
•   5G NR Bands:    1, 3, 5, 7, 8, 28, 38, 40, 41, 77, 78, 79,
                    80, 84 SA/NSA/Sub6
•   Speed:          HSPA 42.2/5.76 Mbps, LTE-A, 5G
');
INSERT IGNORE INTO product
(id, name, company, price, quantity, image, description)
VALUES (1004, 'iPhone 12 Pro', 'Apple', 1200, 5, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/iphone_12.jpg'),
        'Finish

•   Silver
•   Graphite
•   Gold
•   Pacific Blue

Ceramic Shield front, textured matte glass back and stainless steel design

Capacity1

•   128GB
•   256GB
•   512GB

Size and Weight2

•   Width: 3.07 inches (78.1 mm)
•   Height: 6.33 inches (160.8 mm)
•   Depth: 0.29 inch (7.4 mm)
•   Weight: 8.03 ounces (228 grams)

Display

•   Super Retina XDR display
•   6.7 inch (diagonal) all screen OLED display
•   2778 by 1284-pixel resolution at 458 ppi
•   HDR display
•   True Tone
•   Wide color (P3)
•   Haptic Touch
•   2,000,000:1 contrast ratio (typical)
•   800 nits max brightness (typical); 1200 nits max brightness (HDR)
•   Fingerprint-resistant oleophobic coating
•   Support for display of multiple languages and characters simultaneously

The iPhone 12 Pro Max display has rounded corners that follow a beautiful curved design,
and these corners are within a standard rectangle. When measured as a standard rectangular shape,
the screen is 6.68 inches diagonally (actual viewable area is less).

Splash, Water, and Dust Resistant3

•   Rated IP68 (maximum depth of 6 meters up to 30 minutes) under IEC standard 60529

Chip

•   A14 Bionic chip
•   Next generation Neural Engine

Camera

•   Pro 12MP camera system: Ultra Wide, Wide, and Telephoto cameras
•   Ultra Wide: ƒ/2.4 aperture and 120° field of view
•   Wide: ƒ/1.6 aperture
•   Telephoto: ƒ/2.2 aperture
•   2.5x optical zoom in, 2x optical zoom out; 5x optical zoom range
•   Digital zoom up to 12x
•   Night mode portraits enabled by LiDAR Scanner
•   Portrait mode with advanced bokeh and Depth Control
•   Portrait Lighting with six effects (Natural, Studio, Contour, Stage, Stage Mono, High Key Mono)
•   Dual optical image stabilization (Wide and Telephoto)
•   Sensor-shift optical image stabilization
•   Five-element lens (Ultra Wide); six element lens (Telephoto); seven-element lens (Wide)
•   Brighter True Tone flash with Slow Sync
•   Panorama (up to 63MP)
•   Sapphire crystal lens cover
•   100% Focus Pixels (Wide)
•   Night mode (Ultra Wide, Wide)
•   Deep Fusion (Ultra Wide, Wide, Telephoto)
•   Smart HDR 3
•   Apple ProRAW
•   Wide color capture for photos and Live Photos
•   Lens correction (Ultra Wide)
•   Advanced red-eye correction
•   Photo geotagging
•   Auto image stabilization
•   Burst mode
•   Image formats captured: HEIF and JPEG

Video Recording

•   HDR video recording with Dolby Vision up to 60 fps
•   4K video recording at 24 fps, 25 fps, 30 fps, or 60 fps
•   1080p HD video recording at 25 fps, 30 fps, or 60 fps
•   720p HD video recording at 30 fps
•   Sensor-shift optical image stabilization for video (Wide)
•   Optical image stabilization for video (Wide)
•   2.5x optical zoom in, 2x optical zoom out; 5x optical zoom range
•   Digital zoom up to 7x
•   Audio zoom
•   Brighter True Tone flash
•   QuickTake video
•   Slo mo video support for 1080p at 120 fps or 240 fps
•   Time lapse video with stabilization
•   Night mode Time lapse
•   Extended dynamic range for video up to 60 fps
•   Cinematic video stabilization (4K, 1080p, and 720p)
•   Continuous autofocus video
•   Take 8MP still photos while recording 4K video
•   Playback zoom
•   Video formats recorded: HEVC and H.264
•   Stereo recording

TrueDepth Camera

•   12MP camera
•   ƒ/2.2 aperture
•   Portrait mode with advanced bokeh and Depth Control
•   Portrait Lighting with six effects (Natural, Studio, Contour, Stage, Stage Mono, High-Key Mono)
•   Animoji and Memoji
•   Night mode
•   Deep Fusion
•   Smart HDR 3
•   HDR video recording with Dolby Vision up to 30 fps
•   4K video recording at 24 fps, 25 fps, 30 fps, or 60 fps
•   1080p HD video recording at 25 fps, 30 fps, or 60 fps
•   Slo mo video support for 1080p at 120 fps
•   Time lapse video with stabilization
•   Night mode Time lapse
•   Extended dynamic range for video up to 30 fps
•   Cinematic video stabilization (4K, 1080p, and 720p)
•   QuickTake video
•   Wide color capture for photos and Live Photos
•   Lens correction
•   Retina Flash
•   Auto image stabilization
•   Burst mode

Face ID

•   Enabled by TrueDepth camera for facial recognition

Apple Pay

•   Pay with your iPhone using Face ID in stores, within apps, and on the web
•   Send and receive money in Messages
•   Complete purchases made with Apple Pay on your Mac
•   Pay for your ride using Express Transit
');
INSERT IGNORE INTO product
(id, name, company, price, quantity, image, description)
VALUES (1005, 'Galaxy Note20', 'Samsung', 820, 7, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/galaxy_note20.jpg'),
        'General

•   Model:      Samsung Galaxy Note 20
•   Released:   Aug, 2020
•   Status:     Available

Design

•   Type:       Bar
•   Dimensions: 161.6 x 75.2 x 8.3 mm
•   Waterproof: IP68 dust/water resistant (up to 1.5m for 30 mins)

Display

•   Display Type:       Super AMOLED
•   Size:               6.7 inches
•   Resolution:         1080 x 2400 pixels
•   Display Colors:     16M Colors
•   Pixel Density:      393 PPI (pixels per inch)
•   Touch Screen:       Capacitive touchscreen
•   Display Protection: Unspecified
•   Features:           HDR10+
                        Always-on display

Hardware

•   CPU:                Octa-core (2x2.73 GHz Mongoose M5 + 2x2.50 GHz
                        Cortex-A76 + 4x2.0 GHz Cortex-A55) - Global
•   GPU:                Mali-G77 MP11 - Global / Adreno 650 - USA
•   RAM (Memory):       8 GB
•   Internal Storage:   256 GB
•   Memory Card Slot:   No
•   Sensors:            Fingerprint (under display, ultrasonic), accelerometer,
                        gyro, proximity, compass, barometer
                        Samsung Wireless DeX (desktop experience support)
                        ANT+
                        Bixby natural language commands and dictation

Software

•   Operating System:   Android 10 + One UI 2.1
•   User Interface:     Yes

Camera

•   Rear Camera:        12 MP (wide) + 64 MP (telephoto) 3x optical zoom,
                        30x hybrid zoom + 12 MP (ultrawide) Super Steady video
•   Image:              4320p
•   Video:              8K@24fps, 4K@30/60fps, 1080p@30/60/240fps,
                        720p@960fps, HDR10+, stereo sound rec., gyro-EIS & OIS
•   Flash:              LED flash, auto-HDR, panorama
•   Front Camera:       10 MP (wide)

Network

•   SIM:        Nano SIM
•   Dual SIM:   Single SIM (Nano-SIM and/or eSIM) or Hybrid Dual SIM (Nano-SIM, dual stand-by)

Connectivity

•   Wi-fi:              Wi-Fi 802.11 a/b/g/n/ac/6, dual-band, Wi-Fi Direct, hotspot
•   USB:                3.2, Type-C 1.0 reversible connector, USB On-The-Go
•   GPS:                Yes, with A-GPS, GLONASS, BDS, GALILEO
•   NFC:                Yes
•   Wireless Charging:  Fast Qi/PMA wireless charging
•   Headphone Jack:     No

Battery

•   Capacity:   Li-Ion 4300 mAh + Fast charging 25W + USB Power Delivery 3.0
                + Fast Qi/PMA wireless charging + Reverse wireless charging 9W
•   Placement:  Non-removable

Media

•   Video Playback: Yes
•   Video Out:      Yes
•   FM Radio:       FM radio (Snapdragon model only; market/operator dependent)
•   Ring Tones:     Yes
•   Loudspeaker:    Yes
•   Handsfree:      Yes

Data

•   4G LTE:         LTE
•   5G NR Bands:    No
•   Speed:          HSPA 42.2/5.76 Mbps, LTE-A (7CA) Cat20 2000/200 Mbps
');
INSERT IGNORE INTO product
(id, name, company, price, quantity, image, description)
VALUES (1006, 'Galaxy Z Flip', 'Samsung', 1399, 3, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/galaxy_zflip.jpg'),
        'General

•   Model:      Samsung Galaxy Z Flip
•   Released:   February, 2020
•   Status:     Available

Design

•   Type:       Bar
•   Dimensions: Unfolded: 167.9 x 73.6 x 7.2 mm Folded: 87.4 x 73.6 x 17.3 mm
•   Weight:     183 Grams
•   Waterproof: No

Display

•   Display Type:           Foldable Dynamic AMOLED
•   Size:                   6.7 inches
•   Resolution:             1080 x 2636 pixels
•   Display Colors:         16M
•   Pixel Density:          425 PPI (pixels per inch)
•   Touch Screen:           Capacitive touchscreen
•   Display Protection:     Corning Gorilla Glass 6
•   Features:               Cover display: 1.1", Super AMOLED, 112 x 300 pixels

Hardware

•   CPU:                Octa-core (1x2.95 GHz Kryo 485 + 3x2.41 GHz Kryo 485
                        + 4x1.78 GHz Kryo 485)
•   GPU:                Adreno 640 (700 MHz)
•   RAM (Memory):       8 GB
•   Internal Storage:   256 GB
•   Memory Card Slot:   No
•   Sensors:            Fingerprint (side-mounted), accelerometer,
                        gyro, proximity, compass, barometer

Software

•   Operating System:   Android 10.0 + One UI 2
•   User Interface:     Yes

Camera

•   Rear Camera:    12 MP + 12 MP
•   Image:          2160p
•   Video:          2160p@30/60fps, 1080p@60/240fps, 720p@960fps, HDR10+
•   Flash:          LED flash, HDR, panorama
•   Front Camera:   10 MP (wide)

Network

•   SIM:        Nano SIM
•   Dual SIM:   Nano-SIM, eSIM

Connectivity

•   Wi-fi:              Wi-Fi 802.11 a/b/g/n/ac, dual-band, Wi-Fi Direct, hotspot
•   USB:                3.1, Type-C 1.0 reversible connector, USB On-The-Go
•   GPS:                Yes, with A-GPS, GLONASS, GALILEO, BDS
•   NFC:                Yes
•   Wireless Charging:  Fast Qi/PMA wireless charging 15W
•   Headphone Jack:     No

Battery

•   Capacity:       Li-Po 3300 mAh battery + Fast battery charging 15W
                    + Fast wireless charging 9W
•   Placement:      Non-removable

Media

•   Video Playback: Yes
•   Video Out:      Yes
•   FM Radio:       Yes
•   Ring Tones:     Yes
•   Loudspeaker:    Yes, with stereo speakers
•   Handsfree:      Yes

Data

•   4G LTE: LTE band 1(2100), 2(1900), 3(1800), 4(1700/2100), 5(850),
            7(2600), 8(900), 12(700), 13(700), 14(700), 17(700), 18(800),
            19(800), 20(800), 25(1900), 26(850), 28(700), 29(700), 30(2300),
            34(2000), 38(2600), 39(1900), 40(2300), 41(2500), 46(5200), 66(1700/2100), 71(600)
•   Speed:  HSPA 42.2/5.76 Mbps, LTE-A (5CA) Cat16 1000/150 Mbps
');
INSERT IGNORE INTO product
(id, name, company, price, quantity, image, description)
VALUES (1007, 'Galaxy S20', 'Samsung', 732, 12, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/galaxy_s20.jpg'),
        'General

•   Model:      Samsung Galaxy S20 FE 4G
•   Released:   Sept, 2020
•   Status:     Available

Design

•   Type:       Bar
•   Dimensions: 159.8 x 74.5 x 8.4 mm
•   Weight:     190 Grams
•   Waterproof: IP68 dust/water resistant (up to 1.5m for 30 mins)

Display

•   Display Type:           Super AMOLED
•   Size:                   6.5 inches
•   Resolution:             1080 x 2400 pixels
•   Display Colors:         16M
•   Pixel Density:          407 PPI (pixels per inch)
•   Touch Screen:           Capacitive touchscreen
•   Display Protection:     Corning Gorilla Glass
•   Features:               Always-on display
                            120Hz refresh rate
Hardware

•   CPU:                Octa-core (2x2.73 GHz Mongoose M5 +
                        2x2.50 GHz Cortex-A76 + 4x2.0 GHz Cortex-A55)
•   GPU:                Mali-G77 MP11
•   RAM (Memory):       6 GB, 8 GB
•   Internal Storage:   128 GB, 256 GB
•   Memory Card Slot:   microSDXC (uses shared SIM slot)
•   Sensors:            Fingerprint (under display, ultrasonic),
                        accelerometer, gyro, proximity, compass
                        Bixby natural language commands and dictation
Software

•   Operating System:   Android 10 + One UI 2.5
•   User Interface:     Yes

Camera

•   Rear Camera:    12 MP (wide) + 8 MP (telephoto) 3x optical zoom
                    + 12 MP (ultrawide)
•   Image:          2160p
•   Video:          4K, 1080p, gyro-EIS
•   Flash:          LED flash, auto-HDR, panorama
•   Front Camera:   32 MP (wide)

Network

•   SIM:        Nano SIM
•   Dual SIM:   Single SIM (Nano-SIM and/or eSIM) or Hybrid Dual SIM
                (Nano-SIM, dual stand-by)

Connectivity

•   Wi-fi:              Wi-Fi 802.11 a/b/g/n/ac/6, dual-band,
                        Wi-Fi Direct, hotspot
•   USB:                Type-C 1.0 reversible connector,
                        USB On-The-Go
•   GPS:                Yes, with A-GPS, GLONASS, BDS, GALILEO
•   NFC:                Yes
•   Wireless Charging:  No
•   Headphone Jack:     No

Battery

•   Capacity:   Li-Po 4500 mAh + Fast charging 25W, Fast wireless charging 15W,
                Reverse wireless charging 4.5W, and USB Power Delivery 3.0
•   Placement:  Non-removable

Media

•   Video Playback: Yes
•   Video Out:      Yes
•   FM Radio:       Unspecified
•   Ring Tones:     Yes
•   Loudspeaker:    Yes
•   Handsfree:      Yes

Data

•   4G LTE:         LTE
•   5G NR Bands:    No
•   Speed:          HSPA 42.2/5.76 Mbps, LTE-A (5CA) Cat19 1800/200 Mbps
');
INSERT IGNORE INTO product
(id, name, company, price, quantity, image, description)
VALUES (1008, 'Armor 10', 'Ulefone', 550, 7, LOAD_FILE('C:/Git Repo/OceanBlu/src/main/resources/static/img/armor_10.jpg'),
        'General

•   Model:      Ulefone Armor 10
•   Released:   June, 2020
•   Status:     Available

Design

•   Type:       Bar
•   Dimensions: 168.2 x 82 x 15 mm
•   Weight:     320 grams
•   Waterproof: IP68/IP69K dust/water resistant (up to 1.5m for 30 mins)

Display

•   Display Type:           IPS LCD
•   Size:                   6.3 inches
•   Resolution:             1080 x 2340 pixels
•   Display Colors:         16M Colors
•   Pixel Density:          409 ppi (pixels per inch)
•   Touch Screen:           Capacitive touchscreen
•   Display Protection:     Scratch-resistant glass, oleophobic coating

Hardware

•   CPU:                    Octa-core (2x2.2 GHz Cortex-A75 + 6x2.0 GHz Cortex-A55)
•   GPU:                    PowerVR GM9446
•   RAM (Memory):           8 GB
•   Internal Storage:       128 GB
•   Memory Card Slot:       microSDXC (uses shared SIM slot)
•   Sensors:                Fingerprint (side-mounted), accelerometer,
                            gyro, proximity, compass, baroceptor, coulombmeter
                            Endoscope mount (camera sold separately)

Software

•   Operating System:   Android 10
•   User Interface:     Yes

Camera

•   Rear Camera:    64 MP (wide) + 2 MP (depth) + FLIR thermal camera
                    (Lepton module 2.5+5MP, f/2.4)
•   Image:          1080p
•   Video:          1080p@30fps
•   Flash:          quad-LED flash
•   Front Camera:   8 MP

Network

•   SIM:        Nano SIM
•   Dual SIM:   Hybrid Dual SIM (Nano-SIM, dual stand-by)

Connectivity

•   Wi-fi:              Wi-Fi 802.11 a/b/g/n/ac, Wi-Fi Direct, hotspot
•   USB:                USB 2.0, Type-C 1.0 reversible connector, USB On-The-Go
•   GPS:                Yes, with A-GPS, GLONASS, GALILEO, BDS
•   NFC:                Yes
•   Wireless Charging:  No
•   Headphone Jack:     Yes

Battery

•   Capacity:   Li-Po 6600 mAh + Fast charging 18W
•   Placement:  Non-removable

Media

•   Video Playback: Yes
•   Video Out:      Yes
•   FM Radio:       Yes
•   Ring Tones:     Yes
•   Loudspeaker:    Yes
•   Handsfree:      Yes

Data

•   4G LTE:         1, 2, 3, 4, 5, 7, 8, 12, 13, 17, 18, 19,
                    20, 25, 26, 28, 34, 38, 39, 40, 41, 66
•   5G NR Bands:    No
•   Speed:          HSPA 42.2/11.5 Mbps, LTE Cat12 600/150 Mbps
');

--  //////// TRANSACTIONS //////// --

INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (1, 1000, 1, null, '2021-03-06 02:14:24', 'IN', 4, 4, 3800);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (2, 1000, 3, 1, '2021-03-06 02:14:25', 'OUT', 2, 2, 1900);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (3, 1000, 1, null, '2021-03-06 02:14:26', 'IN', 3, 5, 4750);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (4, 1001, 1, null, '2021-03-06 02:15:02', 'IN', 4, 4, 3400);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (5, 1001, 1, null, '2021-03-06 02:15:12', 'IN', 2, 6, 5100);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (6, 1002, 1, null, '2021-03-06 02:21:11', 'IN', 3, 3, 2100);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (7, 1000, 1, null, '2021-03-06 02:22:51', 'IN', 1, 6, 5700);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (8, 1000, 2, 2, '2021-03-06 02:44:25', 'OUT', 1, 5, 4750);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (9, 1002, 4, 3, '2021-03-06 03:12:05', 'OUT', 2, 1, 700);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (10, 1000, 2, 4, '2021-03-06 03:14:25', 'OUT', 1, 4, 3800);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (11, 1001, 3, 5, '2021-03-06 04:14:25', 'OUT', 2, 4, 3400);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (12, 1001, 2, 6, '2021-03-06 04:24:25', 'OUT', 1, 3, 2550);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (13, 1003, 1, null, '2021-04-06 02:14:24', 'IN', 6, 6, 4650);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (14, 1004, 1, null, '2021-04-06 02:18:34', 'IN', 3, 3, 3600);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (15, 1005, 1, null, '2021-04-06 02:29:05', 'IN', 8, 8, 6560);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (16, 1006, 1, null, '2021-04-06 08:02:24', 'IN', 5, 5, 6995);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (17, 1007, 1, null, '2021-04-12 16:55:47', 'IN', 12, 12, 8784);
INSERT IGNORE INTO transaction
(id, product_id, user_id, rating_id, date, code, items, current_items, current_value)
VALUES (18, 1008, 1, null, '2021-04-12 20:12:36', 'IN', 10, 10, 5500);

--  //////// RATINGS //////// --

INSERT IGNORE INTO rating
    (id, rate, review_left, review_title, review_body)
VALUES (1, 4, true, 'Very Good!', 'My first phone and it\'s awesome!');
INSERT IGNORE INTO rating
    (id, rate, review_left, review_title, review_body)
VALUES (2, 4, true, 'Nice Phone!', 'High quality software and hardware, satisfying!');
INSERT IGNORE INTO rating
    (id, rate, review_left, review_title, review_body)
VALUES (3, 4, true, 'Good Quality, A Bit Late', 'I\'ve been waiting for this item to arrive for quite sometime now.
I\'m satisfied with the quality and performance but the delay was a buzz-killer!');
INSERT IGNORE INTO rating
    (id, rate, review_left, review_title, review_body)
VALUES (4, 4, false, null, null);
INSERT IGNORE INTO rating
    (id, rate, review_left, review_title, review_body)
VALUES (5, 4, true, 'Nice Buy!', 'I recommend this phone for everyday use as well as
for heavy duty work, the price was also nice.');
INSERT IGNORE INTO rating
    (id, rate, review_left, review_title, review_body)
VALUES (6, 5, true, 'Excellent Phone!', 'Best phone I\'ve bought so far, so happy to have it.');

--  //////// WISHLIST //////// --

INSERT IGNORE INTO wishlist
    (user_id, product_id)
VALUES (1, 1000);
INSERT IGNORE INTO wishlist
    (user_id, product_id)
VALUES (2, 1001);
INSERT IGNORE INTO wishlist
    (user_id, product_id)
VALUES (3, 1001);
INSERT IGNORE INTO wishlist
    (user_id, product_id)
VALUES (2, 1002);
INSERT IGNORE INTO wishlist
    (user_id, product_id)
VALUES (1, 1001);