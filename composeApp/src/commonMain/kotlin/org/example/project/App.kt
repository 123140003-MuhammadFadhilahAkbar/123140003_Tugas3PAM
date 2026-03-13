package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import tugas3_pam.composeapp.generated.resources.Res
import tugas3_pam.composeapp.generated.resources.profile_pict

private val PrimaryBlue   = Color(0xFF1A73E8)
private val AccentCyan    = Color(0xFF00BCD4)
private val SurfaceLight  = Color(0xFFF8FAFF)
private val CardWhite     = Color(0xFFFFFFFF)
private val TextPrimary   = Color(0xFF1C1C2E)
private val TextSecondary = Color(0xFF6B7280)
private val GreenOnline   = Color(0xFF22C55E)
private val GradientStart = Color(0xFF1A73E8)
private val GradientEnd   = Color(0xFF6C63FF)

@Composable
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary      = PrimaryBlue,
            secondary    = AccentCyan,
            background   = SurfaceLight,
            surface      = CardWhite,
            onPrimary    = Color.White,
            onBackground = TextPrimary,
            onSurface    = TextPrimary,
        )
    ) {
        var showContact by remember { mutableStateOf(false) }
        val scrollState = rememberScrollState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color    = SurfaceLight
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Hero Header
                HeroHeader(
                    name   = "Muhammad Fadhilah Akbar",
                    title  = "Mobile Developer · ITERA '23",
                    status = "Aktif Belajar"
                )

                Spacer(Modifier.height(20.dp))

                StatsRow(modifier = Modifier.padding(horizontal = 20.dp))

                Spacer(Modifier.height(20.dp))

                BioCard(
                    text     = "Mahasiswa Teknik Informatika ITERA angkatan 2023. " +
                            "Fokus pada Mobile Development dan UI/UX Design. " +
                            "Passionate dalam membangun aplikasi multiplatform dengan Kotlin.",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(Modifier.height(16.dp))


                Button(
                    onClick  = { showContact = !showContact },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    shape  = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text(
                        text       = if (showContact) "Sembunyikan Kontak" else "Tampilkan Kontak",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                AnimatedVisibility(
                    visible = showContact,
                    enter   = expandVertically() + fadeIn(),
                    exit    = shrinkVertically() + fadeOut()
                ) {
                    ContactCard(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    text      = "© 2025 · IF25-22017 · ITERA",
                    fontSize  = 12.sp,
                    color     = TextSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun HeroHeader(name: String, title: String, status: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(
                Brush.verticalGradient(colors = listOf(GradientStart, GradientEnd))
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Foto profil dari drawable
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter            = painterResource(Res.drawable.profile_pict),
                    contentDescription = "Foto Profil",
                    contentScale       = ContentScale.Crop,
                    modifier           = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.White, CircleShape)
                )
                // Badge online
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(GreenOnline, CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text       = name,
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold,
                color      = Color.White,
                textAlign  = TextAlign.Center,
                modifier   = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text      = title,
                fontSize  = 13.sp,
                color     = Color.White.copy(alpha = 0.85f),
                fontStyle = FontStyle.Italic
            )

            Spacer(Modifier.height(10.dp))

            // Status chip
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White.copy(alpha = 0.2f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier          = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(GreenOnline, CircleShape)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text       = status,
                        fontSize   = 12.sp,
                        color      = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun StatsRow(modifier: Modifier = Modifier) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(value = "2023", label = "Angkatan", modifier = Modifier.weight(1f))
        StatCard(value = "IF",   label = "Prodi",    modifier = Modifier.weight(1f))
        StatCard(value = "3+",   label = "Proyek",   modifier = Modifier.weight(1f))
    }
}

@Composable
fun StatCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(
        modifier  = modifier,
        shape     = RoundedCornerShape(12.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier            = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
        ) {
            Text(
                text       = value,
                fontSize   = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color      = PrimaryBlue
            )
            Text(text = label, fontSize = 11.sp, color = TextSecondary)
        }
    }
}

@Composable
fun BioCard(text: String, modifier: Modifier = Modifier) {
    Card(
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "👤", fontSize = 16.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    text       = "Tentang Saya",
                    fontWeight = FontWeight.Bold,
                    fontSize   = 15.sp,
                    color      = TextPrimary
                )
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = SurfaceLight)
            Spacer(Modifier.height(8.dp))
            Text(
                text       = text,
                fontSize   = 13.sp,
                color      = TextSecondary,
                lineHeight = 20.sp,
                textAlign  = TextAlign.Justify
            )
        }
    }
}

@Composable
fun ContactCard(modifier: Modifier = Modifier) {
    Card(
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📞", fontSize = 16.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    text       = "Informasi Kontak",
                    fontWeight = FontWeight.Bold,
                    fontSize   = 15.sp,
                    color      = TextPrimary
                )
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = SurfaceLight)
            Spacer(Modifier.height(12.dp))

            InfoItem(emoji = "📧", label = "Email",     value = "muhammad.123140003@student.itera.ac.id")
            Spacer(Modifier.height(10.dp))
            InfoItem(emoji = "📱", label = "Telepon",   value = "+62 853 4258 6196")
            Spacer(Modifier.height(10.dp))
            InfoItem(emoji = "📍", label = "Lokasi",    value = "Lampung, Indonesia")
            Spacer(Modifier.height(10.dp))
            InfoItem(emoji = "🎓", label = "Institusi", value = "Institut Teknologi Sumatera")
        }
    }
}

@Composable
fun InfoItem(emoji: String, label: String, value: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier          = modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier         = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceLight)
        ) {
            Text(text = emoji, fontSize = 18.sp)
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(text = label, fontSize = 11.sp, color = TextSecondary)
            Text(text = value, fontSize = 13.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
        }
    }
}