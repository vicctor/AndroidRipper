.class public final Lcom/g1024l/simpleandroidform/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.kt"


# annotations
.annotation runtime Lkotlin/Metadata;
    bv = {
        0x1,
        0x0,
        0x2
    }
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\u0008\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014\u00a8\u0006\u0007"
    }
    d2 = {
        "Lcom/g1024l/simpleandroidform/MainActivity;",
        "Landroid/support/v7/app/AppCompatActivity;",
        "()V",
        "onCreate",
        "",
        "savedInstanceState",
        "Landroid/os/Bundle;",
        "app_debug"
    }
    k = 0x1
    mv = {
        0x1,
        0x1,
        0x7
    }
.end annotation


# instance fields
.field private _$_findViewCache:Ljava/util/HashMap;


# direct methods
.method public constructor <init>()V
    .registers 3

    .prologue

    const-string/jumbo v0, "TRACE"

    const-string/jumbo v1, "com/g1024l/simpleandroidform/MainActivity:constructor <init>:ENTER "

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I


    .line 6
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public _$_clearFindViewByIdCache()V
    .registers 4

    iget-object v0, p0, Lcom/g1024l/simpleandroidform/MainActivity;->_$_findViewCache:Ljava/util/HashMap;

    if-eqz v0, :cond_9

    iget-object v0, p0, Lcom/g1024l/simpleandroidform/MainActivity;->_$_findViewCache:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    :cond_9
    return-void
.end method

.method public _$_findCachedViewById(I)Landroid/view/View;
    .registers 5

    iget-object v0, p0, Lcom/g1024l/simpleandroidform/MainActivity;->_$_findViewCache:Ljava/util/HashMap;

    if-nez v0, :cond_b

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/g1024l/simpleandroidform/MainActivity;->_$_findViewCache:Ljava/util/HashMap;

    :cond_b
    iget-object v0, p0, Lcom/g1024l/simpleandroidform/MainActivity;->_$_findViewCache:Ljava/util/HashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    if-nez v0, :cond_26

    invoke-virtual {p0, p1}, Landroid/support/v4/app/FragmentActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/g1024l/simpleandroidform/MainActivity;->_$_findViewCache:Ljava/util/HashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_26
    return-object v0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .registers 5
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;
        .annotation build Lorg/jetbrains/annotations/Nullable;
        .end annotation
    .end param

    .prologue

    const-string/jumbo v0, "TRACE"

    const-string/jumbo v1, "com/g1024l/simpleandroidform/MainActivity:onCreate:ENTER "

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I


    .line 9
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 10
    const v0, 0x7f09001b

    invoke-virtual {p0, v0}, Lcom/g1024l/simpleandroidform/MainActivity;->setContentView(I)V

    .line 11
    return-void
.end method
